package com.digital.gpt.chat.vo.base;

import lombok.Data;

/**
 * @author WeJan
 * @since 2020-04-14
 */
@Data
public class PageBaseReq {
    private Integer pageIndex = 1;
    private Integer pageSize = 20;
}
