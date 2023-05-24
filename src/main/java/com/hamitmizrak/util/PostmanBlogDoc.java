package com.hamitmizrak.util;

public class PostmanBlogDoc {


    //  LIST (GET)
    //  http://localhost:2222/blog/api/v1/list

    // DELETE ALL (GET)
    // http://localhost:2222/blog/api/v1/deleteall

    // SPEED DATA (GET)
    // http://localhost:2222/blog/api/v1/speedData

    // FIND (GET)
    // http://localhost:2222/blog/api/v1/find/1

    // DELETE (DELETE)
    // http://localhost:2222/blog/api/v1/delete/1


    // UPDATE
    // http://localhost:2222/blog/api/v1/update/1
     /*
    {
        "header":"başlık",
        "content":"içerik",
        "image":"image.png",
    }
    */

    // CREATE
    // 1.YOL cURL
    /*
    curl --location 'http://localhost:2222/blog/api/v1/create' \
    --header 'Content-Type: application/json' \
    --data '{
        "header":"başlık",
        "content":"içerik",
        "image":"resim.png"
    }'
    */

    // 2.YOL HTTP
    // CREATE
    // http://localhost:2222/blog/api/v1/create
    /*
   POST /blog/api/v1/create HTTP/1.1
    Host: localhost:2222
    Content-Type: application/json
    Content-Length: 78

    {
        "header":"başlık",
        "content":"içerik",
        "image":"resim.png"
    }
      */

}
