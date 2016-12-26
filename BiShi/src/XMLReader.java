import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XMLReader {
	// 配置文件名
	private static String filename = "resources/contract.xml";
	private static Map<String, String> config;
	
	/**
	 * 方法简述：从配置文件中读取参数并保存到Config类中,很多时候程序中会多次使用到配置中的参数, 
	 * 于是设置成静态方法,读取一次后就一直保存其中的参数，不再反复读取
	 * @return 以中文名为键，java字段为值的Map
	 * 时间：2016-11-23
	 * 作者：刘群
	 */
	public static Map<String, String> loadconfig() {
		if (config == null)
			config = getconfig();
		return config;
	}

	/**
	 * 方法简述：SAX进行XML解析得到键值Map
	 * @return
	 * 时间：2016-11-23
	 * 作者：刘群
	 */
	private static Map<String, String> getconfig() {
		Map<String, String> config = new HashMap<String, String>();
		try {
			File f = new File(filename);
			if (!f.exists()) {
				System.out.println("  Error : Config file doesn't exist!");
				System.exit(1);
			}
			SAXReader reader = new SAXReader();
			Document doc;
			doc = reader.read(f);
			Element root = doc.getRootElement();
			Element data;
			Iterator<?> itr = root.elementIterator("VALUE");
			data = (Element) itr.next();

			config.put( data.elementText("ID").trim(),"ID");
			config.put(data.elementText("contractName").trim(),"contractName" );
			config.put( data.elementText("partyA").trim(),"partyA");
			config.put( data.elementText("partyB").trim(),"partyB");
			config.put( data.elementText("contractType").trim(),"contractType");

		} catch (Exception ex) {
			System.out.println("Error : " + ex.toString());
		}
		return config;

	}
}
