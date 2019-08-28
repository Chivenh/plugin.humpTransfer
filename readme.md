# 驼峰格式的辅助转换工具  [HumpTransfer - Plugin Jetbrains](https://plugins.jetbrains.com/plugin/12882-humptransfer/)
# 实现下划线格式文本和驼峰格式文本的互相转换. 
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

## version 0.1.4
 Fix the bug and optimization docs.<br>
  <em>Fix the bug which will make the first char UPPERCASE when transfer a single LOWERCASE word to underline form.</em>
  <p><b>version</b>: 0.1.4</p>
  <b>Since</b> <i> 2019-08-27 10:05 </i>
## Description
  <ul>
      <li>修复原全部为小写字母的单个词语在转换时出现首字母被大写转换问题</li>
  </ul>

## version 0.1.3
 Fix some bugs and optimization codes <br>
  <em>Now,transfer is more accurate and faster! </em>
  <p><b>version</b>: 0.1.3</p>
  <b>Since</b> <i> 2019-08-27 10:05 </i>
  
### Description

 <ul>
  <li>1.修复在转换下划线格式时,如遇前后相同结构的时候,会出现重复转换问题</li>
  <li>2.优化驼峰和下划线转换方式,极大提高了精度和速度</li>
</ul>

## version 0.1.2
 Fix dangerous bug <br>
  <em>Sorry to published the above version</em>
  <p><b>version</b>: 0.1.2</p>
  <b>Since</b> <i> 2019/8/20 18:45 </i>
  
### Description

 <ul>
  <li>1.修复由于过度处理结尾下划线而导致转换下划线失败问题</li>
  <li>2.优化转换过程</li>
</ul>

## version 0.1.1
 <p><b>version</b>: 0.1.1</p>
 <b>Since</b> <i> 2019/8/17 16:51 </i>
 
### Description
  <ul>
      <li>1.修复在转换过程中会丢失字符的问题</li>
      <li>2.现在所有的转换操作支持批量转换</li>
  </ul>