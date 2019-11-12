package cspi_customfunction;

import java.io.File;
import java.util.Calendar;
import java.util.UUID;

import com.tibco.xml.cxf.common.annotations.XPathFunction;
import com.tibco.xml.cxf.common.annotations.XPathFunctionGroup;
import com.tibco.xml.cxf.common.annotations.XPathFunctionParameter;

@XPathFunctionGroup(category = "CSPI Custom Functions", prefix = "CSPI", namespace = "http://cspi.custom.functions", helpText = "Custom defined function")
public class CSPICustomFunctions {

	@XPathFunction(helpText = "인터페이스 로깅 시 사용할 유니크한 36자리 아이디 생성.", parameters = {})
	public String getUUID() {
		
		String uuid = UUID.randomUUID().toString();
		
		return uuid;
	}
	

	@XPathFunction(helpText = "한글(MS949)이 포함된 문자열을 필요한 길이만큼 추출합니다.", parameters = {
			@XPathFunctionParameter(name = "str", optional = false, optionalValue = ""),
			@XPathFunctionParameter(name = "startLen", optional = false, optionalValue = ""),
			@XPathFunctionParameter(name = "substrLen", optional = false, optionalValue = "")
	})
	public static String substringKO(String str, String startLen, String substrLen) {
		int endLen;
		
		int start  = Integer.parseInt(startLen);
		int substr = Integer.parseInt(substrLen);
		
		endLen = start + substr - 1;

		try {
			if (str == null)
				return "";

			byte abyte0[] = str.getBytes("MS949");

			if (abyte0.length < start)
				return "";

			// if( abyte0.length <= endLen )
			// endLen = abyte0.length;
			if (abyte0.length <= endLen) {
				endLen = abyte0.length;
				substr = abyte0.length - start + 1;
			}

			int count = 0;

			byte abyte1[] = new byte[substr];

			for (int l = start; l < endLen + 1; l++) {
				abyte1[count++] = abyte0[l - 1];
			}

			return new String(abyte1, "MS949");
		} catch (Exception e) {
			return null;
		}
	}
	
	@XPathFunction(helpText = "문자열 치환.", parameters = {
			@XPathFunctionParameter(name = "source", optional = false, optionalValue = ""),
			@XPathFunctionParameter(name = "fromCharList", optional = false, optionalValue = ""),
			@XPathFunctionParameter(name = "toCharList", optional = false, optionalValue = "")
	})
	public static String replace(String source, String fromCharList, String toCharList) {
		String result = "";
		result = source.replaceAll(fromCharList, toCharList);
		return result;
	}
	
	@XPathFunction(helpText = "String to Integer", parameters = {
			@XPathFunctionParameter(name = "str_integer", optional = false, optionalValue = "")
	})
	public static int stringToInteger(String str_integer) {
		int result = 0;
		result = Integer.parseInt(str_integer);
		return result;
	}
	
	@XPathFunction(helpText = "Delete Folder(파일 및 폴더 모두 삭제)", parameters = {
			@XPathFunctionParameter(name = "parentPath", optional = false, optionalValue = "")
	})
	public static boolean deleteFolder(String parentPath) {

	    File file = new File(parentPath);
	    String[] fnameList = file.list();
	    if(fnameList == null)  
	    	return false;
	    int fCnt = fnameList.length;
	    String childPath = "";
	    
	    for(int i = 0; i < fCnt; i++) {
	      childPath = parentPath+"/"+fnameList[i];
	      File f = new File(childPath);
	      if( ! f.isDirectory()) {
	        f.delete();   //파일이면 바로 삭제
	      }
	      else {
	        deleteFolder(childPath);
	      }
	    }
	    
	    File f = new File(parentPath);
	    f.delete();   //폴더는 맨 나중에 삭제
	    
	    return true;
	    
	}
	
	@XPathFunction(helpText = "한 주의 요일을 숫자로 추출", parameters = {})
	public static int getDayofWeek(){
		Calendar today = Calendar.getInstance();

		int _dayOfTheWeek = today.get(Calendar.DAY_OF_WEEK);
		
		return _dayOfTheWeek;
	}
	@XPathFunction(helpText = "한글(euckr)이 포함된 문자열을 필요한 길이를 반환합니다.", parameters = {
			@XPathFunctionParameter(name = "strlen", optional = false, optionalValue = "")
	})
	public static int stringlengthKO(String strlen) {
		
		int strlength;
		try {
			if (strlen == null)
				return 0;

			byte abyte0[] = strlen.getBytes("euc-kr");
			strlength = abyte0.length;

			return strlength;
		} catch (Exception e) {
			return 0;
		}
	}
	
	@XPathFunction(helpText = "입력한 charset에 해당하는 문자열(한글포함)을 필요한 길이만큼 추출합니다.", parameters = {
			@XPathFunctionParameter(name = "str", optional = false, optionalValue = ""),
			@XPathFunctionParameter(name = "startLen", optional = false, optionalValue = ""),
			@XPathFunctionParameter(name = "substrLen", optional = false, optionalValue = ""),
			@XPathFunctionParameter(name = "encodingType", optional = false, optionalValue = "")
	})
	public static String substrCharset(String str, String startLen, String substrLen,String encodingType) {
		int endLen;
		int start  = Integer.parseInt(startLen);
		int substr = Integer.parseInt(substrLen);
		
		endLen = start + substr - 1;

		try {
			if (str == null)
				return "";

			byte abyte0[] = str.getBytes(encodingType);

			if (abyte0.length < start)
				return "";

			// if( abyte0.length <= endLen )
			// endLen = abyte0.length;
			if (abyte0.length <= endLen) {
				endLen = abyte0.length;
				substr = abyte0.length - start + 1;
			}

			int count = 0;

			byte abyte1[] = new byte[substr];

			for (int l = start; l < endLen + 1; l++) {
				abyte1[count++] = abyte0[l - 1];
			}

			return new String(abyte1, encodingType);
		} catch (Exception e) {
			return null;
		}
	}
	
	@XPathFunction(helpText = "한글이 포함된 문자열을 필요한 길이를 반환합니다.", parameters = {
			@XPathFunctionParameter(name = "strlen", optional = false, optionalValue = ""),
			@XPathFunctionParameter(name = "encodingType", optional = false, optionalValue = "")
	})
	public static int strlengthCharset(String strlen, String encodingType) {
		
		int strlength;
		try {
			if (strlen == null)
				return 0;

			byte abyte0[] = strlen.getBytes(encodingType);
			strlength = abyte0.length;

			return strlength;
		} catch (Exception e) {
			return 0;
		}
	}
	
	@XPathFunction(helpText = "json 이쁘게 뽑기", parameters = {
			@XPathFunctionParameter(name = "jsonString", optional = false, optionalValue = "")
	})
	public static String jsonPretty(String jsonString) {
        final String INDENT = "    ";
        StringBuffer prettyJsonSb = new StringBuffer();
        int indentDepth = 0;
        String targetString = null;
        try {
	        for(int i=0; i<jsonString.length(); i++) {
	            targetString = jsonString.substring(i, i+1);
	            if(targetString.equals("{")||targetString.equals("[")) {
	                prettyJsonSb.append(targetString).append("\n");
	                indentDepth++;
	                for(int j=0; j<indentDepth; j++) {
	                    prettyJsonSb.append(INDENT);
	                }
	            }
	            else if(targetString.equals("}")||targetString.equals("]")) {
	                prettyJsonSb.append("\n");
	                indentDepth--;
	                for(int j=0; j<indentDepth; j++) {
	                    prettyJsonSb.append(INDENT);
	                }
	                prettyJsonSb.append(targetString);
	            }
	            else if(targetString.equals(",")) {
	                prettyJsonSb.append(targetString);
	                prettyJsonSb.append("\n");
	                for(int j=0; j<indentDepth; j++) {
	                    prettyJsonSb.append(INDENT);
	                }
	            }
	            else {
	                prettyJsonSb.append(targetString);
	            }
	        }

        return prettyJsonSb.toString();
        }catch (Exception e) {
			return jsonString;
		}
       
    }
	
	
}
