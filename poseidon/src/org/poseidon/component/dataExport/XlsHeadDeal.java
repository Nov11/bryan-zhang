package org.poseidon.component.dataExport;

import org.apache.poi.hssf.usermodel.HSSFSheet;

public interface XlsHeadDeal {
    public Integer dealHead(HSSFSheet sheet, Integer rowCnt) throws Exception;
}
