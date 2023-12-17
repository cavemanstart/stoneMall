package com.stone.exception;

import com.stone.model.vo.common.ResultCodeEnum;
import lombok.Data;

@Data
public class StoneException extends RuntimeException{
    private Integer code;
    private String msg;
    public StoneException(Integer code, String msg){
        super(msg);//需要手动调用父类的有参构造
        this.code = code;
        this.msg = msg;
    }
    public StoneException(ResultCodeEnum resultCodeEnum){
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
        this.msg = resultCodeEnum.getMessage();
    }
    @Override
    public String toString() {
        return "StoneException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }

}
