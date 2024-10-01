package com.houseof.johari.controller;

import com.houseof.johari.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/about")
public class AboutController {

    @GetMapping("/contact")
    public ResponseEntity<CustomResponse<ContactUsResponse>> getContactUs() {
        ContactUsResponse contactUsResponse = new ContactUsResponse();
        contactUsResponse.setCompanyName("House of Johari");
        contactUsResponse.setEmail("care@houseofjohari.in");
        contactUsResponse.setPhone("Your Phone Number");

        return ResponseEntity.ok(
                new CustomResponse<>(HttpStatus.OK.value(), "Success", contactUsResponse)
        );
    }

    @GetMapping("/faqs")
    public ResponseEntity<CustomResponse<FAQResponse>> getFAQs() {
        FAQResponse faqResponse = new FAQResponse();
        List<FAQResponse.FAQ> faqs = new ArrayList<>();

        faqs.add(createFAQ("How do I place an order?",
                "To place an order, simply browse through our product collections, select the items you like, and add them to your cart. When you're ready, click on the shopping cart icon to proceed to checkout and complete your purchase. Once done, you will receive an order confirmation via email."));

        faqs.add(createFAQ("How can I track my order?", ""));
        faqs.add(createFAQ("What payment methods do you accept?", ""));
        faqs.add(createFAQ("How does cash on delivery work?", ""));
        faqs.add(createFAQ("How long will it take for my order to arrive?",
                "The delivery time depends on your location and the shipping method selected. Typically, standard shipping takes 3-20 business days within India, while international orders may take 30 business days."));
        faqs.add(createFAQ("Can I change or cancel my order after it's placed?", ""));

        faqResponse.setFaqs(faqs);
        return ResponseEntity.ok(
                new CustomResponse<>(HttpStatus.OK.value(), "Success", faqResponse)
        );
    }

    @GetMapping("/terms")
    public ResponseEntity<CustomResponse<TermsAndConditionsResponse>> getTermsAndConditions() {
        TermsAndConditionsResponse termsResponse = new TermsAndConditionsResponse();
        termsResponse.setContent("Welcome to House of Johari. By accessing or using our website, you agree to comply with and be bound by the following Terms and Conditions. Please read these terms carefully before using our services.\n\n" +
                "1. Introduction\n" +
                "These Terms and Conditions (\"Terms\") govern your use of the House of Johari website (\"Website\"), including the purchase of any products through the Website. By using our Website, you agree to be bound by these Terms. If you do not agree to these Terms, please do not use our Website.\n\n" +
                "2. Eligibility\n" +
                "You must be at least 18 years old to use our Website and make purchases. By using our Website, you represent and warrant that you have the legal capacity to enter into these Terms\n\n" +
                "3. Account Registration\n" +
                "To access certain features of our Website, you may be required to create an account. You agree to provide accurate, current, and complete information during the registration process and to update such information as necessary. You are responsible for safeguarding your account credentials and for any activity that occurs under your account. House of Johari will not be liable for any loss or damage arising from your failure to protect your account information.\n\n" +
                "4. Product Information and Availability\n" +
                "House of Johari strives to provide accurate descriptions and images of our jewellery products. However, we do not guarantee that the product descriptions, images, or other content on our Website are entirely accurate, complete, or error-free. The colours and appearance of products may vary slightly due to individual monitor settings. All products displayed on our Website are subject to availability. We reserve the right to limit the quantity of products we supply, to discontinue any product, and to refuse any order at our sole discretion.\n\n" +
                "5. Pricing and Payment\n" +
                "All prices on our Website are listed in INR and are inclusive of applicable taxes unless otherwise stated. We reserve the right to change the prices of our products at any time without prior notice.\n" +
                "Payment for your order must be made at the time of purchase using one of the accepted payment methods. You agree to provide valid payment information and authorize us to charge the total amount of your order to your payment methods.");

        return ResponseEntity.ok(
                new CustomResponse<>(HttpStatus.OK.value(), "Success", termsResponse));
    }

    @GetMapping("/privacy")
    public ResponseEntity<CustomResponse<PrivacyPolicyResponse>> getPrivacyPolicy() {
        PrivacyPolicyResponse privacyPolicyResponse = new PrivacyPolicyResponse();
        privacyPolicyResponse.setContent("Introduction\n" +
                "Welcome to House of Johari. We value your privacy and are committed to protecting your personal information. This Privacy Policy outlines how we collect, use, and share information about you when you visit our website or make a purchase from our online store.\n\n" +
                "About House of Johari\n" +
                "House of Johari (\"we,\" \"our,\" \"us\") is an online retailer specializing in the sale of exquisite gold and diamond jewellery. Our platform provides customers with a wide range of premium jewellery products, including rings, necklaces, earrings, sets, and more, crafted with precision and care.\n\n" +
                "Scope of Our Services\n" +
                "House of Johari offers a comprehensive shopping experience where customers can:\n" +
                "- Explore and browse our curated collection of gold and diamond jewellery.\n" +
                "- Make secure purchases through our user-friendly online platform.\n" +
                "- Customize select jewellery pieces to suit personal preferences.\n" +
                "- Benefit from insured delivery services that ensure your purchases arrive safely.\n" +
                "- Access dedicated customer support for inquiries, orders, and other services.\n\n" +
                "Information We Collect\n" +
                "To provide you with the best possible service, we collect various types of information, including:\n" +
                "- Personal Information: When you create an account, place an order, or interact with our customer support, we collect information such as your name, email address, phone number, shipping and billing address, and payment details.\n" +
                "- Order Information: Details related to the jewellery items you purchase, including product specifications, customization requests, pricing, and order history.\n" +
                "- Technical Information: Information related to your device, browsing actions, and usage patterns, such as IP address, browser type, and the pages you visit on our site.\n\n" +
                "How We Use Your Information\n" +
                "We use the information we collect for various purposes, including:\n" +
                "- Processing Orders: To handle and deliver your orders, process payments, and manage returns or exchanges.\n" +
                "- Communication: To keep you informed about your orders, provide updates, and respond to your inquiries.");

        return ResponseEntity.ok(
                new CustomResponse<>(HttpStatus.OK.value(), "Success",privacyPolicyResponse));
    }

    private FAQResponse.FAQ createFAQ(String question, String answer) {
        FAQResponse.FAQ faq = new FAQResponse.FAQ();
        faq.setQuestion(question);
        faq.setAnswer(answer);
        return faq;
    }
}
