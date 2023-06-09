package br.wc.aquino.appium.core;

import static br.wc.aquino.appium.core.DriverFactor.getDriver;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;

public class BasePage {
	public void escrever(By by, String texto) {
		getDriver().findElement(by).sendKeys(texto);
	}
	
	public String obterTexto(By by) {
		return getDriver().findElement(by).getText();
	}
	
	public void clicar(By by){
		getDriver().findElement(by).click();
	}
	
	public void clicarPorTexto(String texto){
		clicar((By.xpath("//*[@text='"+texto+"']")));
	}
	
	public void selecionarCombo(By by, String valor) {
		getDriver().findElement(by).click();
		clicarPorTexto(valor);
	}
	
	public String obsterTituloAlert() {
		return obterTexto(By.id("android:id/alertTitle"));
	}
	
	public String obsterMensagemAlert() {
		return obterTexto(By.id("android:id/message"));
	}
	
	public boolean isCheckMarked(By by){
		return getDriver().findElement(by).getAttribute("checked").equals("true");
	}
	
	public boolean existeElementoPorTexto(String texto) {
		List<MobileElement> elementos = getDriver().findElements(By.xpath("//*[@text='"+texto+"']"));
		return elementos.size()>0;
	}
	
	public void tap(int x, int y) {
		new TouchAction(getDriver()).tap(x ,y).perform();
	}
	
	public void scrollUp() {
		scroll(0.9, 0.1);
	}
	
	public void scrollDown() {
		scroll(0.1, 0.9);
	}
	
	public void swipeLeft() {
		swipe(0.1, 0.9);
	}
	
	public void swipeRight() {
		swipe(0.9, 0.1);
	}
	
	
	public void scroll(double inicio, double fim ) {
		Dimension size = DriverFactor.getDriver().manage().window().getSize();
		
		int x=size.width/2;
		int start_y=(int)(size.height*inicio);
		int end_y=(int)(size.height*fim);
		
		new TouchAction(getDriver()).press(x, start_y).waitAction(Duration.ofMillis(500)).moveTo(x,end_y).release().perform();
	}
	
	public void swipe(double inicio, double fim) {
		Dimension size = DriverFactor.getDriver().manage().window().getSize();
		
		int y=size.height/2;
		int start_x=(int)(size.width*inicio);
		int end_x=(int)(size.width*fim);
		
		new TouchAction(getDriver()).press(y, start_x).waitAction(Duration.ofMillis(500)).moveTo(y,end_x).release().perform();
	}
	
	public void swipeElement(MobileElement element, double inicio, double fim) {
		int y= element.getLocation().y+(element.getSize().height/2);
		int start_x=(int)(element.getSize().width*inicio);
		int end_x=(int)(element.getSize().width*fim);
		
		new TouchAction(getDriver()).press(y, start_x).waitAction(Duration.ofMillis(500)).moveTo(y,end_x).release().perform();
	}
	
	public void cliqueLongo(By by) {
		new TouchAction(DriverFactor.getDriver())
		.longPress(DriverFactor.getDriver().findElement(by))
		.perform();
	}
}
