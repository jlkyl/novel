package com.qianqiu.novel.controller;


import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.qianqiu.novel.utils.MyUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class alipay {

    @RequestMapping("pay")
    public void pay(int money, HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws ServletException, IOException {

        String app_id = "2016092100565112";
        String sign_type = "RSA2";
        String charset = "utf-8";
        String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
        String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCdU3IIvnMmg9kCtkjB2I7c7jeU3kLf46kuBm30MEEwAtHFz7hgTn0guV/Vkd8Ufy0qMBwKElHEQ6wbqRdbUeg00S/owNpb8YWQQ4woBjG3gqdG2sM0ghJg/tR624AFVoZr0d747UN66VprmPRAZsMu6knfMluIv70ygrT9HdLk6y0T4gfxNxvDrdUHS07/q8MAdGV3nHqTb/zKIisVgVubjyBxZn2mFEa3mtvhOJYBsTVmrxGheGOb7n2pJnhFSTxO6O6AZGhP+px++JdOmXfMbTP7DwfMLJHlZEL5xZIS6u6+QeTuCEdqyNj7GNYYp20u7VCPKgJU9+GmVjhmFdF/AgMBAAECggEBAIXk3shSTe4ZiDemq4VOqUed9e/cuU2SlAl3r1yDNPeHbsDLaD+OjS+slMNOwmzPT8D38OUhcjV24qMn5ZjyvI02+287sptNkj6VPlNR8j4cFAFZbYjiqoGiX3OwG+RvHzfkBcAVLbkNoMayBi5JRxRGkVhDo2Ney8aRNze0iigIKJCe2QRhrBaEMkxHurAo+BH3B7jwiboeKeYsvnEcQO2EFiMIWW81DxRVdVIrWcNAN6YCQpDQLygk+UaeSHHGGCOJ3MhGyu/+A710uRPMDuyDGT9bHLIArp5AU8NKhB3pKutP3wWPnFNQl+lV9G+N6s2yGFFEH/EGEYsW54KDHAECgYEA1tWbUeM2egTBZYvXyNlQuoF1qCT2xiBknVl+ZPf0NWc21e4U95/8yxtHbG+dWCEzKzrTeo4LQQkX3huciOT1d0oi1rikHzIvCjZL5gEbXC+ph3l4yXNfqt9Qg8UAx9SweMMajsiMTMhjKw8bJQH3mHmhj1CAUHJEv4/HETxat/ECgYEAu3jYN+Az0KJa/ZRydsPvCym9Q1hDlNKfZw6339P+Aw6TUTyIUs3F1EdpkaFWTawWE7CDIhPH5yAJuoY74FJOuvyvxEXmFzjHXk/CU475eAea9qp4l+EcW6VdsDVdNdzKWyf5UVkd0xyu6ubvA8DBVa7ZBPAaHupPP2JOlxxREG8CgYEAuVag351wkwXlPtDNQWZR4gTr1zrEdprdkm3xv/O2CBhaU2dJSXQJp8OJ87+685v4w/PFHHbMuLe6kpAplGRKbDZWJf43AP7PXDUJd40+6GHqFIk0Lj9NqmFr2bIsgTk5YYWYNNRJTbiW7T/kACJZU36jZbtiZ4SdUNIeP/1cmlECgYBD+yVE1ctUS5t9OthrEs/bF26nPhl7nQjqoR6yTrhcuWtYWQACLtYfDSpOAdg+4Dk9OJPExrGFsUt11PlfddlQ2xKNtN8j6pX5MCkCjeUOzos2CUkjoiDbZtLbc+5x+rdyaMNy6UL0bAFN8yIXFU3GWVoe/UYrkcHc9TQUfdMzhwKBgD+afTo89ORFQEJ0jrDqwcT/4r7H5sRuNOeKGLm0IUeYECqYSgxD456CYMm+nc6bwj+AW7ZUpcByvH0I4Uw9+/+s2MDPPkXIJuFZA265mzbUK/7bK53Pymfv6DZprKGqVNzPXBsxA9EQ1MT9XIPaaq6yfYSnq+M9tRFfSXM5Ijin";
        String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvcBMoTfNnnzyuy3NbpuOhrYfSOz988WSK08w1Mxq0H2/G0FocrprW+UcnmMG6D5hRqLZMpTF5j/ONMw0wvn47sdYzT1KRmDD3WvcZGKZvYfCPQPxkedaT8GKTy63wbdjjdTOqVS6RGgkypXNH68zOgoWaB8UJCL60ay7npc2EAMAJBZSx+yAdhRuym9yDyA0/pmAEudMxt1x4iPFV201n9RMIUOtGsb25ID9rM6YNPvCH2TT0lzJEbV8WgB2wrJRX9QGyBAogGt6QSwusHM/vUu6ti1Oa7ASg8D7uQ0BdVZ3BJCpRXo6ZWDKo4zp8686ooxHp8giILbAE6C2zE37WQIDAQAB";

        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl, app_id, merchant_private_key, "", charset, alipay_public_key, sign_type); // 获得初始化的AlipayClient
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();// 创建API对应的request
        alipayRequest.setReturnUrl("http://localhost:8080/success?money="+money+"&userid="+ MyUtil.getuserid(httpRequest.getSession()));
        alipayRequest.setNotifyUrl("http://localhost:8080/index.html");// 在公共参数中设置回跳和通知地址
        alipayRequest.setBizContent("{" + "    \"out_trade_no\":\"2015032001010100" + (int) (Math.random() * 1000) + "\","
                + "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," + "    \"total_amount\":" + money + ","
                + "    \"subject\":\"充值书币\"," + "    \"body\":\"充值书币\","
                + "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\","
                + "    \"extend_params\":{" + "    \"sys_service_provider_id\":\"2088511833207846\"" + "    }" + "  }");// 填充业务参数
        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); // 调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=" + charset);
        httpResponse.getWriter().write(form);// 直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }

    @RequestMapping("success")
    public String success(int money,int userid) {

        return "redirect:/close";
    }
}
