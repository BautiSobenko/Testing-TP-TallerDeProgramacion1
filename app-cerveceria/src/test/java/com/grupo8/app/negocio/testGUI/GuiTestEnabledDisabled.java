package com.grupo8.app.negocio.testGUI;

import com.grupo8.app.controladores.ControladorLogin;

import java.awt.*;

import javax.swing.JButton;
import javax.swing.JTextField;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GuiTestEnabledDisabled
{
    Robot robot;
    ControladorLogin controlador;

    public GuiTestEnabledDisabled()
    {
        try
        {
            robot = new Robot();
        } catch (AWTException e)
        {
        }
    }

    @Before
    public void setUp() throws Exception
    {
        controlador = new ControladorLogin();
    }

    @After
    public void tearDown() throws Exception
    {
        controlador.getVista().esconder();
    }


    @Test
    public void testSoloNombre()
    {
        robot.delay(TestUtils.getDelay());

        JTextField nombre = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), "textFieldUsername");
        JButton btnEntrar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), "btnEntrar");

        TestUtils.clickComponent(nombre, robot);
        TestUtils.tipeaTexto("admin", robot);

        Assert.assertFalse("El boton de entrar deberia estar deshablitado", btnEntrar.isEnabled());
    }

    @Test
    public void testVacios()
    {
        JButton btnEntrar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), "btnEntrar");

        Assert.assertFalse("El boton de entrar deberia estar deshablitado", btnEntrar.isEnabled());
    }

    @Test
    public void testSoloContrasena()
    {
        robot.delay(TestUtils.getDelay());

        JTextField password = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), "textFieldContrasena");
        JButton btnEntrar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), "btnEntrar");

        TestUtils.clickComponent(password, robot);
        TestUtils.tipeaTexto("admin1234", robot);

        Assert.assertFalse("El boton de entrar deberia estar deshablitado", btnEntrar.isEnabled());
    }

    @Test
    public void testLleno()
    {
        robot.delay(TestUtils.getDelay());

        JTextField nombre = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), "textFieldUsername");
        JTextField password = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(), "textFieldContrasena");

        JButton btnEntrar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(), "btnEntrar");

        TestUtils.clickComponent(nombre, robot);
        TestUtils.tipeaTexto("admin", robot);

        TestUtils.clickComponent(password, robot);
        TestUtils.tipeaTexto("admin1234", robot);

        Assert.assertTrue("El boton de entrar deberia estar habilitado", btnEntrar.isEnabled());
    }


}
