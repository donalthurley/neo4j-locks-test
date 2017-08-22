@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.7')

import groovyx.net.http.RESTClient
import groovyx.net.http.HttpResponseException
import static groovyx.net.http.ContentType.*
import groovy.json.*
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

/*
*
* Test if you can just remove a 'fake' property to get a write lock
*
* Switch this url below to check the 2 scenarios
*/

//def url = "http://localhost:8080/createLicense"
def url = "http://localhost:8080/createLicenseOnlyRemove"

def RESTClient restClient = new RESTClient("http://localhost:8080/")
try {
    def resp = restClient.get(path: "http://localhost:8080/createCustomer/1", requestContentType : JSON)
} catch (HttpResponseException e) {
    println "Status: " + e.statusCode + ", Error Message: " + e.message
}

try {
    def resp = restClient.get(path: "http://localhost:8080/createProduct/1", requestContentType : JSON)
} catch (HttpResponseException e) {
    println "Status: " + e.statusCode + ", Error Message: " + e.message
}


ExecutorService executor = Executors.newFixedThreadPool(20);

futures = []
for (i in 1..10) {
    futures.add(CompletableFuture.runAsync(new RestThread(url, i), executor))
}
CompletableFuture.allOf((CompletableFuture[]) futures.toArray(new CompletableFuture[futures.size()])).join()
println 'finished'

executor.shutdown()
if (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
    List<Runnable> droppedTasks = executor.shutdownNow()
    println "Rejected tasks: {}", droppedTasks.size()
}

class RestThread implements Runnable {
    RESTClient restClient
    String url
    int i

    RestThread(String url, int i) {
        this.url = url
        this.restClient = new RESTClient(url)
        this.i = i

    }

    @Override
    void run() {
        try {
            def resp = restClient.get(path: getUrl(), requestContentType : JSON)

            println "Job: " + i + ", Status: " + resp.status

        } catch (HttpResponseException e) {
            println "Job: " + i + ", Status: " + e.statusCode + ", Error Message: " + e.message
        }
    }
}

