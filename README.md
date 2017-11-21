# coveiot

Project is build on the java platform with spring MVC.

Software used.
1.  Java 8
2.  Spring boot
3.  spring MVC
4.  H2 InMemory database
4.  Apache REST client

Application can be validated with  the below areas:-

1. Create a restful api for user registration with following fields.

	http://localhost:8080/user/register
		{"name":"abc","email":"*****@gmail.com","zip":"80239"}
		# NOTE: zip code should be from United States since this application is configured with us locale.

2. Create a restful api to get login for registered user. 

	http://localhost:8080/user/getlogin/1
		
3. User opens given login-link. If login-link is valid and not expired. Show user details. 

	User can get the url from the previous registered user's email which is generated with login request.
	
4. Create a restful api to get current temperature by pincode 

	sample url : http://localhost:8080/user/login/1?tm=2017-11-21T15:55:47.906

5. Write a schedule which updates temperature-info in database every minute for all saved records older more than 1 minute.

	-TODO-
	
-- end --