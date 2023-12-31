package ch.zli.m223.ksh20.coworking_project.controller.web;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class WebController {
    protected String urlRoot = "http://localhost:8080/api/v1";

    protected <T> T[] getObjects(String apiUrl, Class<T[]> clazz) throws HttpClientErrorException {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(apiUrl, clazz);
    }

    protected <T> T postObject(String apiUrl, Object object, Class<T> clazz) throws HttpClientErrorException {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(apiUrl, object, clazz);
    }

    protected String getErrorPage(HttpClientErrorException e) {
        if (e.getStatusCode().value() == 403)
            return "error/403";
        if (e.getStatusCode().value() == 404)
            return "error/404";
        if (e.getStatusCode().value() == 400)
            return "error/400";
        if (e.getStatusCode().value() == 401)
            return "error/401";
        return "error/500";
    }

    protected <T> T sendPostRequest(String url, Object data, Class<T> clazz) throws HttpClientErrorException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> request = new HttpEntity<>(data, headers);

        return postObject(urlRoot + url, request, clazz);
    }
}
