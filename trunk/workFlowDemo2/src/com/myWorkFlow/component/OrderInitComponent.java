package com.myWorkFlow.component;

import com.myWorkFlow.base.FlowContext;

public class OrderInitComponent extends FlowComponent{
	public boolean excuteWithContext(FlowContext context) {
		System.out.println("������ʼ������");
		return true;
	}
}
