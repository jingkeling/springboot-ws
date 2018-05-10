package com.kolin.pojo.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.print.DocFlavor;

/**
 * 聊天基本信息
 *
 * @Author jingkeling
 * @Date 2018/5/8 21:55
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatVO {

    private String username;

    private String message;

    private String avator;

}
