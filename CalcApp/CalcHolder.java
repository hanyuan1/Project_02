package CalcApp;

/**
* CalcApp/CalcHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Calculator.idl
* Thursday, December 3, 2015 3:14:04 PM PST
*/

public final class CalcHolder implements org.omg.CORBA.portable.Streamable
{
  public CalcApp.Calc value = null;

  public CalcHolder ()
  {
  }

  public CalcHolder (CalcApp.Calc initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = CalcApp.CalcHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    CalcApp.CalcHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return CalcApp.CalcHelper.type ();
  }

}
