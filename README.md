# RestaurantService 👋

## Frameworks and Language used:
 - Spring
 - Spring boot
 - My-Sql
 - Java


 ## Data Flow
 #### Controller
---Food----
 - addFood
 - deletedFood
 - getAllFood

 ---Order----
 - placeOrder
 - getOrders
 - updateOrder

 ---Role----
 - addRole

 ---User----
 - signUp
 - signIn

 #### Service
  ---Food----
 - addFood
 - deletedFood
 - getAllFood

 ---Order----
 - placeOrder
 - getOrders
 - updateOrder

 ---Role----
 - addRole

 ---User----
 - signUp
 - signIn

 #### Repository
 ---Food----

 IFoodRepository

 ---Order----

 IOrderRepository

 ---Role----

 IRoleRepository

 ---Token----

 ITokenRepository

 ---User----

 IUserRepository

 #### Model
 ---Food----
 - foodId
 - foodName
 - foodDescription
 - foodPrice
 - foodImage
 
 ---Order----
 - orderId
 - quantity
 - status
 - User
 - food

 ---Role----
 - roleId
 - roleType
 - roleDescription

 ---User----
 - userId
 - userName
 - password
 - email
 - role

---AuthenticationToken----
- tokenId
- token
- tokenCreationDate
- user

#### dto
 ---SignInInput----
 - email
 - password
 
 ---SignInOutput----
 - status
 - token

 ---SignUpInput----
 - userId
 - userName
 - password
 - email
 - role

 ---SignUpOutput----
 - status
 - message
 
### Project Summary
Created a simple Restaurant service model with User access authentication to provide different role and access to different user like:- Admin,Visitor,User. Each user is authenticated and granted access based on their emai land token to perform operations like add new food item, order food etc.

### How to use in your system?
 - Just Simply clone this reposit
 - Start your server
 - Perform operations 
