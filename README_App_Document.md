# spring-boot Application
# project - Sequence generator
# Document to run the code

# Description :- Sequence generator is a spring boot application that generates a sequence of numbers in the decreasing order till 0. 
# It generates a string of result for the provided input of Goal: and Step: 
	 Inputs: step, goal

# To test the api's for thie spring-boot application Swagger2 is integrated and enabled along with this application.
# To test the business logic locally Junit test cases are also written to simulate test scenarios.

# Import
# After checking-out this project or downloading the zip, import the project as existing maven project in Eclipse 
# Once imported in eclipse perform mvn:clean install on the project, once build is successful RUN the spring boot application -
# Run the SequenceGeneratorApplication by "run as Java application" using the main() method or you can use the following command mvn spring-boot:run. After executing the main() method.
# Your console will look like as shown below - 

1-09 20:16:59.950  INFO 10660 --- [  restartedMain] pertySourcedRequestMappingHandlerMapping : Mapped URL path [/v2/api-docs] onto method [springfox.documentation.swagger2.web.Swagger2Controller#getDocumentation(String, HttpServletRequest)]
2021-01-09 20:17:00.052  INFO 10660 --- [  restartedMain] o.s.s.concurrent.ThreadPoolTaskExecutor  : Initializing ExecutorService 'applicationTaskExecutor'
2021-01-09 20:17:00.084  INFO 10660 --- [  restartedMain] o.s.b.d.a.OptionalLiveReloadServer       : LiveReload server is running on port 35729
2021-01-09 20:17:00.280  INFO 10660 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8081 (http) with context path ''
2021-01-09 20:17:00.282  INFO 10660 --- [  restartedMain] d.s.w.p.DocumentationPluginsBootstrapper : Context refreshed
2021-01-09 20:17:00.307  INFO 10660 --- [  restartedMain] d.s.w.p.DocumentationPluginsBootstrapper : Found 1 custom documentation plugin(s)
2021-01-09 20:17:00.337  INFO 10660 --- [  restartedMain] s.d.s.w.s.ApiListingReferenceScanner     : Scanning for api listing references
2021-01-09 20:17:00.525  INFO 10660 --- [  restartedMain] c.p.s.SequenceGeneratorApplication       : Started SequenceGeneratorApplication in 3.159 seconds (JVM running for 3.678)

# Once the application is up, use below swagger UI to test the API's 

# Swagger UI to test application api's (Endpoints)
http://localhost:8081/swagger-ui.html#/

# Steps to simulate testing - 
# API: - /api/generate (generateSequence)
# Step 1:   Click on "Number request Controller" and click on Post Mapping /api/generate
#           Click on Try It out 
#           It takes a request object with "goal" and "step" {
#                                                               "goal": "string",
#                                                               "step": "string"
#                                                             }

# Step 2: Provide numeric (Integer) value to RequestNumber object where Goal should be greater than step for calculting sequence correctly, else you might face error resonse.
# Step 3: Eample:- 
  {
     "goal": "100",
     "step": "13"
  }

# Step 4: Click on "Execute", if a sequnce is created then a responce is generated and returned which is the UUID for this sequnce. 
#         Example: "d27f9551-63f4-42f0-8316-257de87f4db3"


# API: /api/bulkGenerate (bulkGenerateSequence)
# Step 1: Click on bulkGenerateSequence api (in swagger UI). It takes a list of requestNumber object and generates an UUID for this task 
	 Input example: 
                [
                  {
                    "goal": "100",
                    "step": "17"
                  },
                {
                    "goal": "50",
                    "step": "9"
                  },
                {
                    "goal": "77",
                    "step": "18"
                  },
                {
                    "goal": "65",
                    "step": "8"
                  }
                ]

# Response Output: 
        UUID = de47d20c-4978-4bc5-be44-a26d25ccc7b3

#API:- /api/getAll (getAllSequences)
# To see all the Sequnces available in memory you can use app api "/api/getAllSequences" 

# API:- /api/tasks/{id} (getTaskResult)
# It takes 2 inputs id and action 

	action=get_numlist 
# And id can be any of the previously generated UUID in memory to be retrieved. 
	Example: d27f9551-63f4-42f0-8316-257de87f4db3
# It returns the result for this task, which is the sequence generated previously.

# API:- /api/tasks/{id}/status
# It takes 1 inputs UUID for the task and returns the status of the task.
	Input: 
      	d27f9551-63f4-42f0-8316-257de87f4db3
	Response Output: 
      	SUCCESS

