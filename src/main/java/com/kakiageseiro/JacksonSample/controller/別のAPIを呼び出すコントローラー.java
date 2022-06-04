package com.kakiageseiro.JacksonSample.controller;


import com.kakiageseiro.JacksonSample.infrastructure.model.externalapi.Auth;
import com.kakiageseiro.JacksonSample.infrastructure.model.externalapi.apibase.ApiLinkFailureException;
import com.kakiageseiro.JacksonSample.infrastructure.model.externalapi.hoge.Hoge依頼型;
import com.kakiageseiro.JacksonSample.infrastructure.model.externalapi.hoge.Hoge連携クライアントファクトリ;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class 別のAPIを呼び出すコントローラー {
    private static Hoge連携クライアントファクトリ Hoge連携クライアントファクトリ;

    @GetMapping("/test/call")
    public void call(){
        try {
            long personID = 1L;
            String userID = "userID123";
            String languageCode = "ja";
            int projectID = 1;
            Payload payload = new Payload(987);
            Hoge依頼型<Payload> hoge依頼 = new Hoge依頼型<>(Auth.作成する(personID, userID, languageCode), projectID, payload);

            String なんか指定しなきゃいけないIDかなんか = "tenpura";

            String Hoge連携APIパス = "";
            Hoge連携クライアントファクトリ.getクライアント(Hoge連携APIパス, なんか指定しなきゃいけないIDかなんか).post(hoge依頼);
        } catch (ApiLinkFailureException e) {
            // logger.info(e.getMessage());
            throw new IllegalStateException("message.error.qualitymanagement.defect_input.registration.fail");
        }
    }
}
