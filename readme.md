# 驼峰格式的辅助转换工具:
# 实现将选中下划线格式文本进行驼峰的转换和将选中驼峰格式文本向下划线格式文件的转换. 
## 此插件会在Edit,Code以及Refactor三个按钮分组中添加对应按钮组.
## HumpTransfer 有下面4个针对性的动作
- Transfer2Hump
    - 将非驼峰字符串转换成驼峰字符串形式
- Transfer2Hump-UpperChar0
    - 将非驼峰字符串转换成驼峰字符串形式,首字母大写
- Transfer2UnderLine
    - 将驼峰字符串转换成下划线字符串形式
- Transfer2UnderLine-UpperCase
    - 将驼峰字符串转换成下划线字符串形式,强制转大写
    ---
# Change Notes
 <p><b>version</b>: 0.1.1</p>
 <b>Since</b> <i> 2019/8/17 16:51 </i>
 
### Description
  <ul>
      <li>1.修复在转换过程中会丢失字符的问题</li>
      <li>2.现在所有的转换操作支持批量转换</li>
  </ul>