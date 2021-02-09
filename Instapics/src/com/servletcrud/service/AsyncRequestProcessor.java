package com.servletcrud.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncContext;

public class AsyncRequestProcessor implements Runnable {

	private AsyncContext asyncContext;
	

	public AsyncRequestProcessor() {
	}

	public AsyncRequestProcessor(AsyncContext asyncCtx) {
		this.asyncContext = asyncCtx;
		
	}

	@Override
	public void run() {
		System.out.println("Async Supported? "
				+ asyncContext.getRequest().isAsyncSupported());
		
		try {
			PrintWriter out = asyncContext.getResponse().getWriter();
			
		} catch (Exception e) {
			e.printStackTrace();
		}//complete the processing
		asyncContext.complete();
	}

}