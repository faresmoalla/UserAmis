package tn.esprit.pdf;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import tn.esprit.entity.User;
import tn.esprit.repository.UserRepository;
import tn.esprit.service.UserService;











public class PDFGeneratorService {
	@Autowired
	UserService RecServ;
	@Autowired
	public static UserRepository RecRepo;
	

	
	
	
	

	
	
	
	public static  ByteArrayInputStream customerPDFReport(List<User> listp) {
		

		Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        try {
        	
        	PdfWriter.getInstance(document, out);
            document.open();
         
			// Add Text to PDF file ->
			Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, BaseColor.RED);
			Paragraph para = new Paragraph( "All Users", font);
			
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);
			
			
			Font font2 = FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.RED);
			
			Paragraph para2 = new Paragraph( "All Users", font2);
			para.setAlignment(Element.ALIGN_LEFT);
			document.add(para2);
			document.add(Chunk.NEWLINE);
			
			
			
        	PdfPTable table = new PdfPTable(5);
        	// Add PDF Table Header ->
			Stream.of("Date", "contenu_rec", "type","etat","user")
			    .forEach(headerTitle -> {
			          PdfPCell header = new PdfPCell();
			          Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			          header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			          header.setHorizontalAlignment(Element.ALIGN_CENTER);
			          header.setBorderWidth(2);
			          header.setPhrase(new Phrase(headerTitle, headFont));
			          table.addCell(header);
			    });
						
			
			   
			 for (User pub: listp	) {
				
	        
	            	
	           
	            
	            	PdfPCell date = new PdfPCell(new Phrase(String.valueOf(pub.getAdresse())));
	            
	            	date.setPaddingLeft(4);
	            	date.setVerticalAlignment(Element.ALIGN_MIDDLE);
	            	date.setHorizontalAlignment(Element.ALIGN_CENTER);
	                table.addCell(date);

	                PdfPCell content = new PdfPCell(new Phrase(pub.getEmailUser()));
	                content.setPaddingLeft(4);
	                content.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                content.setHorizontalAlignment(Element.ALIGN_LEFT);
	                table.addCell(content);

	                
	                PdfPCell nblikes = new PdfPCell(new Phrase(String.valueOf(pub.getDateNaissance())));
	                nblikes.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                nblikes.setHorizontalAlignment(Element.ALIGN_RIGHT);
	                nblikes.setPaddingRight(4);
	                table.addCell(nblikes);
	                
	                
	                PdfPCell nbdislike = new PdfPCell(new Phrase(String.valueOf(pub.getNomUser())   ));
	                nbdislike.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                nbdislike.setHorizontalAlignment(Element.ALIGN_RIGHT);
	                nbdislike.setPaddingRight(4);
	                table.addCell(nbdislike);
	                
	                PdfPCell user = new PdfPCell(new Phrase(String.valueOf(pub.getPrenomUser()   )));
	                user.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                user.setHorizontalAlignment(Element.ALIGN_RIGHT);
	                user.setPaddingRight(4);
	                table.addCell(user);
	                
	            
	                
	                
	            }
            
            
            
            
            
            document.add(table);
            
            document.close();
            
            
          
            
            
        }catch(DocumentException e) {
        }
        
		return new ByteArrayInputStream(out.toByteArray());
	}
	
	
	
	
	
	
}
