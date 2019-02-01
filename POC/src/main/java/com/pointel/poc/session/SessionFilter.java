package com.pointel.poc.session;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionFilter implements Filter {
	@Override
	public void doFilter(ServletRequest filterRequest, ServletResponse filterRponse, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) filterRequest;
		HttpServletResponse response = (HttpServletResponse) filterRponse;
		HttpSession session = request.getSession();

		response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		String uri = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1,
				request.getRequestURI().length());

		List<String> list = Arrays.asList("Add", "CheckFileName", "GetProjectName", "GetVoiceName", "GetAudioPath",
				"addFilter", "checkExcelFile", "readUploadExcelFile", "checkSingleAudio", "Trace", "OnloadProp",
				"Filter", "getGender", "updateFilter", "DeleteAudioRecord", "FileExist", "AudioFilePath", "PlayAudio",
				"Logout");

		if (session.getAttribute("POCUSERID") == null) {
			System.out.println(session.getAttribute("POCUSERID"));
			response.sendRedirect("Login");
		} else {
			if (session.getAttribute("POCUSERROLE").toString().equalsIgnoreCase("Admin")) {
				filterChain.doFilter(request, response);
			} else {
				if (list.contains(uri)) {
					filterChain.doFilter(request, response);
				} else {
					response.sendRedirect("Trace");
				}

			}
		}

	}
}
