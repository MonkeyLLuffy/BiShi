import java.util.Scanner;


public class Main {
	
	public static void main(String[] args)
	{
		Scanner inScanner = new Scanner(System.in);
		while (inScanner.hasNext()) {
		 String str1 = inScanner.next();
		 String str2 = inScanner.next();
		 String string1  =  str1.toLowerCase();
		 String string2  =  str2.toLowerCase();
		 getLCString(string1.toCharArray(), string2.toCharArray());
			
		}
	}
	 public static void  getLCString(char[] str1, char[] str2) 
	 {  
	        int len1, len2;  
	        len1 = str1.length;  
	        len2 = str2.length;  
	        int maxLen = len1 > len2 ? len1 : len2;  
	  
	        int[] max = new int[maxLen];// ������Ӵ����ȵ�����  
	        int[] maxIndex = new int[maxLen];// ������Ӵ������������������  
	        int[] c = new int[maxLen];  
	        //String result = "";
	        int i, j;  
	        for (i = 0; i < len2; i++) {  
	            for (j = len1 - 1; j >= 0; j--) {  
	                if (str2[i] == str1[j]) {  
	                    if ((i == 0) || (j == 0))  
	                        c[j] = 1;  
	                    else  
	                        c[j] = c[j - 1] + 1;//��ʱC[j-1]�����ϴ�ѭ���е�ֵ����Ϊ��û�����¸�ֵ  
	                } else {  
	                    c[j] = 0;  
	                }  
	  
	                // ����Ǵ�������ʱֻ��һ�������,����Ҫ�Ѻ������0;  
	                if (c[j] > max[0]) {  
	                    max[0] = c[j];  
	                    maxIndex[0] = j;  
	  
	                    for (int k = 1; k < maxLen; k++) {  
	                        max[k] = 0;  
	                        maxIndex[k] = 0;  
	                    }  
	                   // result = "slfl";
	                }  
	                // �ж������ͬ���ȵ��Ӵ�  
	                else if (c[j] == max[0]) {  
	                    for (int k = 1; k < maxLen; k++) {  
	                        if (max[k] == 0) {  
	                            max[k] = c[j];  
	                            maxIndex[k] = j;  
	                         //   result = "slfl";
	                            break; // �ں����һ����Ҫ�˳�ѭ����  
	                        }  
	                    }  
	                }  
	            }
	            //�ж��Ƿ�ƥ��
	         
	          // return result;
	        }  
	        //��ӡ����ַ���  
	        for (j = 0; j < maxLen; j++) {  
	            if (max[j] > 0) {  
	                System.out.print("result=");  
	                for (i = maxIndex[j] - max[j] + 1; i <= maxIndex[j]; i++)  
	                    System.out.print(str1[i]);  
	                
	            }
	            
	        }  
	    }  
	  

}
