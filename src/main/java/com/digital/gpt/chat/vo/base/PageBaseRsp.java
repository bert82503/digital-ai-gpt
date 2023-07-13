package com.digital.gpt.chat.vo.base;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 分页数据对象构建
 * Tips: PageBaseRsp.of(total, list);
 *
 * @author WeJan
 * @since 2020-04-14
 */
@Data
@AllArgsConstructor(staticName = "of")
public class PageBaseRsp<T> {
    private long total;
    private List<T> list;
}
