package mailteset

class TestMailController {
          def mailService
    def index = {
        def accessCodes=[]
        for (i in 0..100 - 1) {
            accessCodes.add(getAlphaNumeric(10))
        }
      //  String lang = ConvertUtils.getChompedString(params.lang)
        String emailId ="arifulla@sonata-software.com"
        String entityForeignId = "ABCD"
        String header = 'HotelCode,AccessCode\n\n'
        String line
        String mailres
        mailres = header
        accessCodes.each {
            line = entityForeignId + "," + it + "\n"
            mailres += line
   }
        log.info("Accesscodes created- Mail being sent...")
        String subval=""
        String lang="de"
        if(lang == 'de'){
            subval = "Zugangscodes für nicht TUI-Gäste"
        }
        else if (lang == 'en'){
            subval = "Entity Access Codes for HotelCode"
        }
        println mailres
        mailService.sendMail {
            multipart true
            to emailId
            subject "$subval $entityForeignId"
            html(view: "../testMail/maildata",model:[params:lang])
            attachBytes "HotelCode_AccessCodes.csv", "text/csv", mailres.getBytes()
        }// end mail service method
     /*   mailService.sendMail {
            multipart true
            to
            subject "Entity Access Codes for HotelCode "
            body( " hello this is test email...... cheers :)")
            attachBytes 'h.csv','text/csv',mailres.getBytes()
        }// end mail service method
*/
    }

    public String getAlphaNumeric(int len) {
        final String ALPHA_NUM = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuffer sb = new StringBuffer(len);
        for (int i = 0; i < len; i++) {
            int ndx = (int) (Math.random() * ALPHA_NUM.length());
            sb.append(ALPHA_NUM.charAt(ndx));
        }
        return sb.toString();
    }

}
