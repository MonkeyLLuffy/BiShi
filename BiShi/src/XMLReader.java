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
	// �����ļ���
	private static String filename = "resources/contract.xml";
	private static Map<String, String> config;
	
	/**
	 * �����������������ļ��ж�ȡ���������浽Config����,�ܶ�ʱ������л���ʹ�õ������еĲ���, 
	 * �������óɾ�̬����,��ȡһ�κ��һֱ�������еĲ��������ٷ�����ȡ
	 * @return ��������Ϊ����java�ֶ�Ϊֵ��Map
	 * ʱ�䣺2016-11-23
	 * ���ߣ���Ⱥ
	 */
	public static Map<String, String> loadconfig() {
		if (config == null)
			config = getconfig();
		return config;
	}

	/**
	 * ����������SAX����XML�����õ���ֵMap
	 * @return
	 * ʱ�䣺2016-11-23
	 * ���ߣ���Ⱥ
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
