package lab3;
TEST UIResponsiveness
  UI_COMPONENT uiComponent = setupUIComponent(1024)
  ASSERT_TRUE(uiComponent.adjustsToScreenSize(1024), "UI should adjust to width of 1024 pixels")
END TEST


/**
 * se analiza un test para validar el tamaño de la interfaz de usuario de una aplicion en este 
 * caso un ancho de 1024 pixeles 
 * 
 */


public class UIComponentTest {
    @Test
    public void testUIResponsivenessToScreenSize() {
        UIComponent uiComponent = new UIComponent(1024);
        assertTrue("UI should adjust to width of 1024 pixels", uiComponent.adjustsToScreenSize(1024));
    }
}

/**
 * escribiremos el test en java para validar el tamaño de la interfaz en este caso solo se puede el ancho a 1024 pixeles
 * 
 */
