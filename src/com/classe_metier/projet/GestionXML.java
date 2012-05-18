package com.classe_metier.projet;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import android.os.Environment;

public class GestionXML 
{	
	public static Document document;
	public static XPath xpath;

	public int nbr_livraison(String nom_fichier)
	{
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
		{
			int nbLivraisons=0;
		
			try
			{
				File fichier = new File(nom_fichier);
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				factory.setNamespaceAware(true);
				DocumentBuilder builder = factory.newDocumentBuilder();
				document=builder.parse(fichier);
				xpath=XPathFactory.newInstance().newXPath();
				
				try 
				{
					Object result = null;
					XPathExpression expr = xpath.compile("//livraison/expediteur/nom");
					result = expr.evaluate(document, XPathConstants.NODESET);
					NodeList nodes = (NodeList) result;
			     	nbLivraisons=nodes.getLength();
				
				} catch (XPathExpressionException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			
			return nbLivraisons;
		}
		else
		{
			return 0;
		}		
	}
	
	public String recup_donnee (String nom_fichier, String nodename)
	{
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
		{
			String string ="azertyuio";
		
			try
			{
				File fichier = new File(nom_fichier);
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				factory.setNamespaceAware(true);
				DocumentBuilder builder = factory.newDocumentBuilder();
				document=builder.parse(fichier);
				xpath=XPathFactory.newInstance().newXPath();
				
				try
				{				
					XPathExpression expr = xpath.compile(nodename);
					string = (String)expr.evaluate(document, XPathConstants.STRING);
					
				} catch (XPathExpressionException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}catch(Exception e)
			{
				e.printStackTrace();
			}		
			return string;
		}
		else
		{
			return "non";
		}
	}
	
	public int recup_id (String nom_fichier, String nodename)
	{
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
		{
			String string ="azertyuio";
			int ID=0;
		
			try
			{
				File fichier = new File(nom_fichier);
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				factory.setNamespaceAware(true);
				DocumentBuilder builder = factory.newDocumentBuilder();
				document=builder.parse(fichier);
				xpath=XPathFactory.newInstance().newXPath();
				
				try 
				{				
					XPathExpression expr = xpath.compile(nodename);
					string = (String)expr.evaluate(document, XPathConstants.STRING);
					ID=Integer.parseInt(string);
					
				} catch (XPathExpressionException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}catch(Exception e)
			{
				e.printStackTrace();
			}		
			return ID;
		}
		else
		{
			return 0;
		}
	}
	
}

