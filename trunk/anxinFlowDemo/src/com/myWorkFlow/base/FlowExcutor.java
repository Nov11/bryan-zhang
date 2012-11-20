package com.myWorkFlow.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.myWorkFlow.component.FlowComponent;
import com.myWorkFlow.component.FlowSelector;
import com.myWorkFlow.listener.FlowCmpManage;
import com.myWorkFlow.listener.FlowRlaManage;


public class FlowExcutor {
	private FlowContext context;

	public FlowExcutor(FlowContext context) {
		this.context = context;
	}

	//ִ��
	public void excute(String processNo) throws Throwable{
		Flow flow = this.buildFlow(processNo);
		//�����Ƿ�ع���־λ
		boolean isRollBack = false;
		//����ع����˳���б�
		List<FlowComponent> rollBackCmpList = new ArrayList<FlowComponent>();
		//ȡ����flow�����е�����б�
		List<FlowComponent> allCmpList = flow.getCmpList();
		for (FlowComponent cmp : allCmpList){
			//���ѡȡ���������
			if (cmp instanceof FlowSelector){
				FlowComponent cmpSlt = ((FlowSelector)cmp).getComponent(context);
				if (cmpSlt == null){
					throw new Exception("cannot get sellector component");
				}
				cmp = cmpSlt;
			}
			
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
		String unionKey = null;
		for(CmpTypeEnum ftEnum : flowRla.getCmpTypeEnumList()){
			String key = this.getKeyFromProcess(processNo, ftEnum);//ȡ�������Ӧ��key
			//��ʼװ�����
			unionKey = ftEnum.name() + "_" + key;
			List<FlowComponent> cmpList = FlowCmpManage.getFlowCmp(unionKey);
			
			if (cmpList != null){
				//���ͬһ����ҵ��ȡ��2����������index��������
				if (cmpList.size() > 1){
					Collections.sort(cmpList, new Comparator<FlowComponent>() {
						public int compare(FlowComponent o1, FlowComponent o2) {
							return (o1.getIndex() - o2.getIndex());
						};
					});
				}
				
				for (FlowComponent cmp : cmpList){
					if (cmp.isBuild(context)){//�ж��������Ƿ�װ��
						flow.addCmp(cmp);
					}
				}
			}
		}
		return flow;
	}
	
	private String getKeyFromProcess(String processNo, CmpTypeEnum flEnum){
		String[] processArray = processNo.split("_");
		if (flEnum.equals(CmpTypeEnum.biz)){
			return processArray[1];
		}else if(flEnum.equals(CmpTypeEnum.pay)){
			return processArray[2];
		}else if(flEnum.equals(CmpTypeEnum.orderInit)){
			return processArray[1];
		}else if(flEnum.equals(CmpTypeEnum.orderEnd)){
			return processArray[1];
		}else if(flEnum.equals(CmpTypeEnum.discount)){
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
