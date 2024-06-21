package sales.api.common;

public class StringInfo {

	public  static String getLocal(String sub_domain) {

		/*
		NEW_KEY : FNHDDSBFNGWWWLTJ
		40A89B86B1540AC8264C9674D001D84EA882AD12A3B2F3374C53171B533E3265
		 */

		String addr = null;

		if (sub_domain != null && "payes.co.kr".equals(sub_domain)) {
			//4A1607224670D1A0B270344B94654DECA882AD12A3B2F3374C53171B533E3265

			//epaynet, seyfertpay
			//addr = "6084D286C5552D8290987FF3F53A1ACAA882AD12A3B2F3374C53171B533E3265";

			//martpay,barocash
			//addr = "4C84F6464DB7CC5655B223B332062741A882AD12A3B2F3374C53171B533E3265";

			//pay24
			//addr = "BB20F83720275FF5B1069A5B720F0D75A882AD12A3B2F3374C53171B533E3265";

			//payper
			//addr = "BB20F83720275FF5B1069A5B720F0D75A882AD12A3B2F3374C53171B533E3265";

			//CHBC
			//addr = "40A89B86B1540AC8264C9674D001D84EA882AD12A3B2F3374C53171B533E3265";

		} else if (sub_domain != null && "paynpay.co.kr".equals(sub_domain)) {

			addr = "77586329321D7447D552D0016EC8B441A882AD12A3B2F3374C53171B533E3265";

		} else if (sub_domain != null && "epaynet.co.kr".equals(sub_domain)) {

			addr = "6084D286C5552D8290987FF3F53A1ACAA882AD12A3B2F3374C53171B533E3265";

		} else if (sub_domain != null && "pay-24.net".equals(sub_domain)) {

			addr = "BB20F83720275FF5B1069A5B720F0D75A882AD12A3B2F3374C53171B533E3265";

		} else if (sub_domain != null && "localhost".equals(sub_domain)) {

			//epaynet, seyfertpay
			//addr = "6084D286C5552D8290987FF3F53A1ACAA882AD12A3B2F3374C53171B533E3265";

			//martpay,barocash
			addr = "4C84F6464DB7CC5655B223B332062741A882AD12A3B2F3374C53171B533E3265";

			//pay24
			//addr = "BB20F83720275FF5B1069A5B720F0D75A882AD12A3B2F3374C53171B533E3265";

			//payper
			//addr = "BB20F83720275FF5B1069A5B720F0D75A882AD12A3B2F3374C53171B533E3265";

			//CHBC
			//addr = "40A89B86B1540AC8264C9674D001D84EA882AD12A3B2F3374C53171B533E3265";
			
			//Bnet1
			//addr = "40A89B86B1540AC8264C9674D001D84EA882AD12A3B2F3374C53171B533E3265";

		} else if (sub_domain != null && "inipos.co.kr".equals(sub_domain)) {

			addr = "090B20A154C42EE981CA9E5027CBF741A882AD12A3B2F3374C53171B533E3265";

		} else if (sub_domain != null && "dailyreport.co.kr".equals(sub_domain)) {

			addr = "3A6294C6CCD606086645B6A09707DAB1A882AD12A3B2F3374C53171B533E3265";

		} else if (sub_domain != null && "bspsolution.com".equals(sub_domain)) {

			addr = "ABF072493DF8F7FBE030AE03069D8E68A882AD12A3B2F3374C53171B533E3265";

		} else if (sub_domain != null && "dndnetworks.net".equals(sub_domain)) {

			addr = "FE13E58D4FB3DFEB06582981182EDC2AA882AD12A3B2F3374C53171B533E3265";

		} else if (sub_domain != null && "martpay.co.kr".equals(sub_domain)) {

			addr = "4C84F6464DB7CC5655B223B332062741A882AD12A3B2F3374C53171B533E3265";
		} else if (sub_domain != null && "chbc.co.kr".equals(sub_domain)) {

			addr = "40A89B86B1540AC8264C9674D001D84EA882AD12A3B2F3374C53171B533E3265";
		} else if (sub_domain != null && "bnet1.co.kr".equals(sub_domain)) {

			addr = "40A89B86B1540AC8264C9674D001D84EA882AD12A3B2F3374C53171B533E3265";
		} else if (sub_domain != null && "owra.net".equals(sub_domain)) {

			addr = "6084D286C5552D8290987FF3F53A1ACAA882AD12A3B2F3374C53171B533E3265";
		} else if (sub_domain != null && "payper.co.kr".equals(sub_domain)) {

			addr = "BB20F83720275FF5B1069A5B720F0D75A882AD12A3B2F3374C53171B533E3265";
		} else if (sub_domain != null && ("seyfertpay.com".equals(sub_domain) || "seyfertpay.net".equals(sub_domain))) {

			addr = "6084D286C5552D8290987FF3F53A1ACAA882AD12A3B2F3374C53171B533E3265";
		} else if ((sub_domain != null) && ("barocash.co.kr".equals(sub_domain))) {
			
			addr = "4C84F6464DB7CC5655B223B332062741A882AD12A3B2F3374C53171B533E3265";
		} else if ((sub_domain != null) && ("new-martpay.co.kr".equals(sub_domain))) {
			
			addr = "4C84F6464DB7CC5655B223B332062741A882AD12A3B2F3374C53171B533E3265";
			
		} else if ((sub_domain != null) && ("eosorder.co.kr".equals(sub_domain))) {
			
			addr = "4C84F6464DB7CC5655B223B332062741A882AD12A3B2F3374C53171B533E3265";
			
		} else if ((sub_domain != null) && ("eoskrental.com".equals(sub_domain))) {
			
			addr = "4C84F6464DB7CC5655B223B332062741A882AD12A3B2F3374C53171B533E3265";
			
		}  else if ((sub_domain != null) && ("plusonepay.co.kr".equals(sub_domain))) {
			
			addr = "4C84F6464DB7CC5655B223B332062741A882AD12A3B2F3374C53171B533E3265";
			
		}
//		System.out.println(addr);
		return addr ;
	}
}