package word  ;

import com4j.*;

@IID("{C75AD98A-74E9-49FE-8BF1-544839CC08A5}")
public interface ChartArea extends Com4jObject {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "Name"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(110) //= 0x6e. The runtime will prefer the VTID if present
  @VTID(7)
  java.lang.String name();


  /**
   * @return  Returns a value of type java.lang.Object
   */

  @DISPID(235) //= 0xeb. The runtime will prefer the VTID if present
  @VTID(8)
  @ReturnValue(type=NativeType.VARIANT)
  java.lang.Object select();


  /**
   * <p>
   * Getter method for the COM property "Parent"
   * </p>
   * @return  Returns a value of type com4j.Com4jObject
   */

  @DISPID(150) //= 0x96. The runtime will prefer the VTID if present
  @VTID(9)
  @ReturnValue(type=NativeType.Dispatch)
  com4j.Com4jObject parent();


  /**
   * <p>
   * Getter method for the COM property "Border"
   * </p>
   * @return  Returns a value of type word.ChartBorder
   */

  @DISPID(128) //= 0x80. The runtime will prefer the VTID if present
  @VTID(10)
  word.ChartBorder border();


  /**
   * @return  Returns a value of type java.lang.Object
   */

  @DISPID(111) //= 0x6f. The runtime will prefer the VTID if present
  @VTID(11)
  @ReturnValue(type=NativeType.VARIANT)
  java.lang.Object clear();


  /**
   * @return  Returns a value of type java.lang.Object
   */

  @DISPID(113) //= 0x71. The runtime will prefer the VTID if present
  @VTID(12)
  @ReturnValue(type=NativeType.VARIANT)
  java.lang.Object clearContents();


  /**
   * @return  Returns a value of type java.lang.Object
   */

  @DISPID(551) //= 0x227. The runtime will prefer the VTID if present
  @VTID(13)
  @ReturnValue(type=NativeType.VARIANT)
  java.lang.Object copy();


  /**
   * <p>
   * Getter method for the COM property "Font"
   * </p>
   * @return  Returns a value of type word.ChartFont
   */

  @DISPID(146) //= 0x92. The runtime will prefer the VTID if present
  @VTID(14)
  word.ChartFont font();


  /**
   * <p>
   * Getter method for the COM property "Shadow"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(103) //= 0x67. The runtime will prefer the VTID if present
  @VTID(15)
  boolean shadow();


  /**
   * <p>
   * Setter method for the COM property "Shadow"
   * </p>
   * @param prop Mandatory boolean parameter.
   */

  @DISPID(103) //= 0x67. The runtime will prefer the VTID if present
  @VTID(16)
  void shadow(
    boolean prop);


  /**
   * @return  Returns a value of type java.lang.Object
   */

  @DISPID(112) //= 0x70. The runtime will prefer the VTID if present
  @VTID(17)
  @ReturnValue(type=NativeType.VARIANT)
  java.lang.Object clearFormats();


  /**
   * <p>
   * Getter method for the COM property "Height"
   * </p>
   * @return  Returns a value of type double
   */

  @DISPID(123) //= 0x7b. The runtime will prefer the VTID if present
  @VTID(18)
  double height();


  /**
   * <p>
   * Setter method for the COM property "Height"
   * </p>
   * @param prop Mandatory double parameter.
   */

  @DISPID(123) //= 0x7b. The runtime will prefer the VTID if present
  @VTID(19)
  void height(
    double prop);


  /**
   * <p>
   * Getter method for the COM property "Interior"
   * </p>
   * @return  Returns a value of type word.Interior
   */

  @DISPID(129) //= 0x81. The runtime will prefer the VTID if present
  @VTID(20)
  word.Interior interior();


  /**
   * <p>
   * Getter method for the COM property "Fill"
   * </p>
   * @return  Returns a value of type word.ChartFillFormat
   */

  @DISPID(1663) //= 0x67f. The runtime will prefer the VTID if present
  @VTID(21)
  word.ChartFillFormat fill();


  /**
   * <p>
   * Getter method for the COM property "Left"
   * </p>
   * @return  Returns a value of type double
   */

  @DISPID(127) //= 0x7f. The runtime will prefer the VTID if present
  @VTID(22)
  double left();


  /**
   * <p>
   * Setter method for the COM property "Left"
   * </p>
   * @param prop Mandatory double parameter.
   */

  @DISPID(127) //= 0x7f. The runtime will prefer the VTID if present
  @VTID(23)
  void left(
    double prop);


  /**
   * <p>
   * Getter method for the COM property "Top"
   * </p>
   * @return  Returns a value of type double
   */

  @DISPID(126) //= 0x7e. The runtime will prefer the VTID if present
  @VTID(24)
  double top();


  /**
   * <p>
   * Setter method for the COM property "Top"
   * </p>
   * @param prop Mandatory double parameter.
   */

  @DISPID(126) //= 0x7e. The runtime will prefer the VTID if present
  @VTID(25)
  void top(
    double prop);


  /**
   * <p>
   * Getter method for the COM property "Width"
   * </p>
   * @return  Returns a value of type double
   */

  @DISPID(122) //= 0x7a. The runtime will prefer the VTID if present
  @VTID(26)
  double width();


  /**
   * <p>
   * Setter method for the COM property "Width"
   * </p>
   * @param prop Mandatory double parameter.
   */

  @DISPID(122) //= 0x7a. The runtime will prefer the VTID if present
  @VTID(27)
  void width(
    double prop);


  /**
   * <p>
   * Getter method for the COM property "AutoScaleFont"
   * </p>
   * @return  Returns a value of type java.lang.Object
   */

  @DISPID(1525) //= 0x5f5. The runtime will prefer the VTID if present
  @VTID(28)
  @ReturnValue(type=NativeType.VARIANT)
  java.lang.Object autoScaleFont();


  /**
   * <p>
   * Setter method for the COM property "AutoScaleFont"
   * </p>
   * @param prop Mandatory java.lang.Object parameter.
   */

  @DISPID(1525) //= 0x5f5. The runtime will prefer the VTID if present
  @VTID(29)
  void autoScaleFont(
    @MarshalAs(NativeType.VARIANT) java.lang.Object prop);


  /**
   * <p>
   * Getter method for the COM property "Format"
   * </p>
   * @return  Returns a value of type word.ChartFormat
   */

  @DISPID(1610743831) //= 0x60020017. The runtime will prefer the VTID if present
  @VTID(30)
  word.ChartFormat format();


  /**
   * <p>
   * Getter method for the COM property "Application"
   * </p>
   * @return  Returns a value of type com4j.Com4jObject
   */

  @DISPID(148) //= 0x94. The runtime will prefer the VTID if present
  @VTID(31)
  @ReturnValue(type=NativeType.Dispatch)
  com4j.Com4jObject application();


  /**
   * <p>
   * Getter method for the COM property "Creator"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(149) //= 0x95. The runtime will prefer the VTID if present
  @VTID(32)
  int creator();


  // Properties:
}
