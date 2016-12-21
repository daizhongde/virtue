//package person.daizhongde.virtue.struts2;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.apache.struts2.StrutsStatics;
//
//import com.opensymphony.xwork2.ActionContext;
//
///**
// * 获取上下文信息.
// * @author w144786
// *
// */
//public class ActionContextUtil {
//	public ActionContextUtil() {
//		// TODO Auto-generated constructor stub
//	}
//	
//	/**
//	 * 获取HttpServletResponse.
//	 * @return
//	 */
//	public static HttpServletResponse getResponse() {
//		ActionContext context = ActionContext.getContext();
//		HttpServletResponse response = (HttpServletResponse)context.get(StrutsStatics.HTTP_RESPONSE);
//		return response;
//	}
//	
//	/**
//	 * 获取HttpServletRequest.
//	 * @return
//	 */
//	public static HttpServletRequest getRequest() {
//		ActionContext context = ActionContext.getContext();
//		HttpServletRequest request = (HttpServletRequest)context.get(StrutsStatics.HTTP_REQUEST);
//		return request;
//	}
//	
////	/**
////	 * 从session中获取用户信息.
////	 * @return
////	 */
////	public static UserDto getUserInfoFromSession() {
////		HttpSession httpSession = getRequest().getSession();
////		UserDto user = (UserDto) httpSession.getAttribute(LoginConstants.LOGIN_USER);
////		return user;
////	}
//}
