package AAA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;

public class maini {
	public static void main(String[] args) throws Exception {
		System.out.println(getMACAddress("http://www.baidu.com"));
	}
	  /**  
     * ͨ��IP��ַ��ȡMAC��ַ  
     * @param ip String,127.0.0.1��ʽ  
     * @return mac String  
     * @throws Exception  
     */    
    public static String getMACAddress(String ip) throws Exception {    
        String line = "";    
        String macAddress = "";    
        final String MAC_ADDRESS_PREFIX = "MAC Address = ";    
        final String LOOPBACK_ADDRESS = "127.0.0.1";    
        //���Ϊ127.0.0.1,���ȡ����MAC��ַ��    
        if (LOOPBACK_ADDRESS.equals(ip)) {    
            InetAddress inetAddress = InetAddress.getLocalHost();    
            //ò�ƴ˷�����ҪJDK1.6��    
            byte[] mac = NetworkInterface.getByInetAddress(inetAddress).getHardwareAddress();    
            //��������ǰ�mac��ַƴװ��String    
            StringBuilder sb = new StringBuilder();    
            for (int i = 0; i < mac.length; i++) {    
                if (i != 0) {    
                    sb.append("-");    
                }    
                //mac[i] & 0xFF ��Ϊ�˰�byteת��Ϊ������    
                String s = Integer.toHexString(mac[i] & 0xFF);    
                sb.append(s.length() == 1 ? 0 + s : s);    
            }    
            //���ַ�������Сд��ĸ��Ϊ��д��Ϊ�����mac��ַ������    
            macAddress = sb.toString().trim().toUpperCase();    
            return macAddress;    
        }    
        //��ȡ�Ǳ���IP��MAC��ַ    
        try {    
            Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);    
            InputStreamReader isr = new InputStreamReader(p.getInputStream());    
            BufferedReader br = new BufferedReader(isr);    
            while ((line = br.readLine()) != null) {    
                if (line != null) {    
                    int index = line.indexOf(MAC_ADDRESS_PREFIX);    
                    if (index != -1) {    
                        macAddress = line.substring(index + MAC_ADDRESS_PREFIX.length()).trim().toUpperCase();    
                    }    
                }    
            }    
            br.close();    
        } catch (IOException e) {    
            e.printStackTrace(System.out);    
        }    
        return macAddress;    
    }    
      
}
