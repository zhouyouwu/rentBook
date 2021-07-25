package com.zhouyouwu.mapper;

import com.zhouyouwu.model.Transfer;
import com.zhouyouwu.model.vo.TransferSearchParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Administrator
 */
@Mapper
public interface TransferMapper {

    List<Transfer> getTransfer(@Param("searchParam") TransferSearchParam searchParam);

    int transfer(@Param("transfer") Transfer transfer);
}
