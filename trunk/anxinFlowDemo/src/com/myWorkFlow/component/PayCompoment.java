package com.myWorkFlow.component;

import com.myWorkFlow.base.FlowContext;

public class PayCompoment extends FlowComponent {

	public boolean excuteWithContext(FlowContext context) {
		System.out.println("����pfȥ���֧��");
		return true;
	}
	
	public void rollbackWithContext(FlowContext context) {
		System.out.println("�ع�֧��");
		
	}

}
