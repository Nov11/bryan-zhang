package com.myWorkFlow.component;

import com.myWorkFlow.base.FlowContext;

public class BizComponentV2 extends FlowComponent {

	public boolean excuteWithContext(FlowContext context) {
		System.out.println("��ȡBiz������ҵ��V2");
		return true;
	}
	
	public void rollbackWithContext(FlowContext context) {
		System.out.println("�ع�ҵ��");
	}

}
