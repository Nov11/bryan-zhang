package com.myWorkFlow.component;

import com.myWorkFlow.base.FlowContext;

public class BizCompoment extends FlowComponent {

	public void excuteWithContext(FlowContext context) {
		System.out.println("��ȡBiz������ҵ��");
	}
	
	public void rollbackWithContext(FlowContext context) {
		System.out.println("�ع�ҵ��");
	}

}
