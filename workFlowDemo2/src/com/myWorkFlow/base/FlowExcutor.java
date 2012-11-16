package com.myWorkFlow.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.myWorkFlow.component.FlowComponent;
import com.myWorkFlow.listener.FlowCmpManage;
import com.myWorkFlow.listener.FlowRlaManage;


public class FlowExcutor {
	private FlowContext context;

	public FlowExcutor(FlowContext context) {
		this.context = context;
	}

	//ִ��
	public void excute(String processNo){
		Flow flow = this.buildFlow(processNo);
		//�����Ƿ�ع���־λ
		boolean isRollBack = false;
		//����ع����˳���б�
		List<FlowComponent> rollBackCmpList = new ArrayList<FlowComponent>();
		//ȡ����flow�����еİ�˳��ִ�е�����б�
		List<FlowComponent> allCmpList = flow.getAllComponent();
		for (FlowComponent cmp : allCmpList){
			boolean excuteFlag = cmp.excuteWithContext(context);
			rollBackCmpList.add(cmp);
			if (!excuteFlag){
				isRollBack = true;
				break;
			}
		}
		
		//��ʼ�ع�����
		if (isRollBack){
			//�������д���
			Collections.reverse(rollBackCmpList);
			for (FlowComponent cmp : rollBackCmpList){
				cmp.rollbackWithContext(context);
			}
		}
	}
	
	//��װFlow
	private Flow buildFlow(String processNo){
		Flow flow = new Flow();
		FlowRelation flowRla = FlowRlaManage.getFlowRla(processNo);
		FlowType ft = null;
		for(FlowTypeEnum ftEnum : flowRla.getFlowTypeEnumList()){
			ft = new FlowType();
			ft.setTypeEnum(ftEnum);//�����������
			String key = this.getKeyFromProcess(processNo, ftEnum);//ȡ�������Ӧ��key
			
			//��ʼװ�����
			List<FlowComponent> cmpList = FlowCmpManage.getFlowCmp(ftEnum, key);
			if (cmpList != null){
				for (FlowComponent cmp : cmpList){
					if (cmp.isBuild(context)){//�ж��������Ƿ�װ��
						ft.addComponent(cmp);
					}
				}
			}
			flow.addFlowType(ft);
		}
		return flow;
	}
	
	private String getKeyFromProcess(String processNo, FlowTypeEnum flEnum){
		String[] processArray = processNo.split("_");
		if (flEnum.equals(FlowTypeEnum.biz)){
			return processArray[1];
		}else if(flEnum.equals(FlowTypeEnum.pay)){
			return processArray[2];
		}else if(flEnum.equals(FlowTypeEnum.orderInit)){
			return processArray[1];
		}else if(flEnum.equals(FlowTypeEnum.orderEnd)){
			return processArray[1];
		}else if(flEnum.equals(FlowTypeEnum.discount)){
			return processArray[1];
		}
		return null;
	}
	
	public FlowContext getContext() {
		return context;
	}

	public void setContext(FlowContext context) {
		this.context = context;
	}
}
