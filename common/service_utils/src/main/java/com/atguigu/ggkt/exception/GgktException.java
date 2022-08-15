package com.atguigu.ggkt.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author wzj
 * @Date 2022/8/15 14:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GgktException extends RuntimeException {
    private Integer code;
    private String msg;
}
