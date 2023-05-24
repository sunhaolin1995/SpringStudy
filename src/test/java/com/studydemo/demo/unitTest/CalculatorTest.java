package com.studydemo.demo.unitTest;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CalculatorTest {
    private static final Logger logger = LoggerFactory.getLogger(CalculatorTest.class);

    @Test
       public void testAdd() {
        // 创建 Calculator 的模拟对象
        Calculator calculator = mock(Calculator.class);

        // 设置模拟对象的行为
        when(calculator.add(2, 3)).thenReturn(6);

        // 调用被测试的方法
        int result = calculator.add(2, 3);

        // 打印输出
        logger.info("Result: {}", result);

        // 验证结果
        assertEquals(5, result);

        // 验证方法是否被调用
        verify(calculator).add(2, 3);
    }

}
