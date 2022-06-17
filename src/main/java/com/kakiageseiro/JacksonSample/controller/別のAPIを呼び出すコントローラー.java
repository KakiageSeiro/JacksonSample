package com.kakiageseiro.JacksonSample.controller;


import com.kakiageseiro.JacksonSample.controller.結果型.jzip住所結果型;
import com.kakiageseiro.JacksonSample.infrastructure.model.externalapi.apibase.ApiLinkFailureException;
import com.kakiageseiro.JacksonSample.infrastructure.model.externalapi.hoge.Hoge連携クライアントファクトリ;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class 別のAPIを呼び出すコントローラー {
    @Autowired
    private Hoge連携クライアントファクトリ hoge連携クライアントファクトリ;

    @GetMapping("/test/call")
    public jzip住所結果型 call() {
        try {
//            long personID = 1L;
//            String userID = "userID123";
//            String languageCode = "ja";
//            int projectID = 1;
//            Payload payload = new Payload(987);
//            Hoge依頼型<Payload> hoge依頼 = new Hoge依頼型<>(Auth.作成する(personID, userID, languageCode), projectID, payload);

            // サンプルとして無料で使えるAPIをよびだしてみる
            // http://project.iw3.org/zip_search_x0401/
            //
            // リクエスト書式
            // http://api.thni.net/jzip/X0401/JSON/郵便番号上3桁の数値/郵便番号下4桁の数値.js
            // 例　http://api.thni.net/jzip/X0401/JSON/064/0941.js
            String Hoge連携APIパス = "jzip/X0401/JSON/144/0052.js";

            // Hoge連携クライアントファクトリ.生成(Hoge連携APIパス, なんか指定しなきゃいけないIDかなんか).post(hoge依頼);
            jzip住所結果型 res = hoge連携クライアントファクトリ.生成(Hoge連携APIパス).get();
            System.out.println("■" + res.toString());

            return res;
        } catch (ApiLinkFailureException e) {
            // logger.info(e.getMessage());
            throw new IllegalStateException("message.error.qualitymanagement.defect_input.registration.fail");
        }
    }
}
