import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("Should return a Book")
    request {
        method GET()
        url "/api/books/1"
    }
    response {
        status OK()
        headers {
            contentType applicationJson()
        }
        body(id: 1, name: "Java Concurrency")
    }
}