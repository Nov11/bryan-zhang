package com.myWorkFlow.component;

import com.myWorkFlow.base.FlowContext;

public class DiscountComponent2 extends FlowComponent {

	@Override
	public boolean excuteWithContext(FlowContext context) {
		System.out.println("ִ�еֿ۲���2");
		return true;
	}
	
	public void rollbackWithContext(FlowContext context){
		System.out.println("�ع��ֿ۲���2");
	}

}
