## SEMojo Analysis and Design

### Team

<HR style="FILTER: progid:DXImageTransform.Microsoft.Glow(color=#987cb9,strength=10)" width="100%" color=#987cb9 SIZE=1>

11711621赵志翔 11711623朱昉 11812110程千帆 11812211徐品深

### Executive abstract 
<HR style="FILTER: progid:DXImageTransform.Microsoft.Glow(color=#987cb9,strength=10)" width="100%" color=#987cb9 SIZE=1>
The main purpose of this project is to design a website to provide the services of storing and uploading files, sharing files, online editing and multi-person collaboration. Using this website, Administrators can manage user permissions, test files uploaded by users, and provide solutions for user feedback. Users can upload, download files, access files made public by others, and provide comments and questions. This site solves the problem of lack of communication between users and providers, and users need open source technologies that are difficult to solve. This website aims to provide a convenient platform for administrators to publish information, users to query files, upload and download. In addition, a custom user interface was designed for each student to "follow" their areas of interest to simplify their use.



### Description

<HR style="FILTER: progid:DXImageTransform.Microsoft.Glow(color=#987cb9,strength=10)" width="100%" color=#987cb9 SIZE=1>

#### motivation

<HR style="FILTER: progid:DXImageTransform.Microsoft.Glow(color=#987cb9,strength=10)" width="80%" color=#987cb9 SIZE=1>

##### User view:

There are some developers who hold some advanced software engineering techniques in hand: Perhaps the number of techs is not enough to integrate as software to provide service. And there are other concerns such as distribution problem(发行渠道).

There are also some developers or small companies who want to use these techniques to improve their software: they cannot search for the right techniques to satisfy their requirements.



This project aims to achieve the following functions:

1. Develop a user-friendly website for these users to upload, save, share, update code and files. 
2. Create a forum for users and developers to communicate, and for users and administrators to give feedback.



**Silver bullets:**

- Information retrieval based on inverted index structure and vector space model.

#### Feature Description

<HR style="FILTER: progid:DXImageTransform.Microsoft.Glow(color=#987cb9,strength=10)" width="80%" color=#987cb9 SIZE=1>

##### User stories

Story 1:

Little Shen is a third-year student at SUStech. He wants to finish his design work after class.  But he is not able to find a solution to one key technology. It frustrated him all day. Little Fang, an excellent student majoring in computer science, hears about little Shen’s story.  He feels that the techniques he has invented can help little Shen.  Through the introduction of friends, Shen used Fang’s technology to complete his work and invited Fang to have a meal to express his gratitude.

Story 2:

Shen believes that there are still many people who are troubled by this technical problem, and Fang hopes to gain more benefits through his techniques. They both think there is a huge demand for tech exchange. Together, they decided to build a platform to service those who are eager for software engineering services and those who want to turn their advanced techniques into profit.

##### UML user cases

<img src="C:\Users\hp\AppData\Roaming\Typora\typora-user-images\1603159684654.png" alt="1603159684654" style="zoom:30%;" />

##### Mockup demonstration

1. The homepage is mainly responsible for showing the function and meaning of the platform and dynamically displaying recent popular products. In addition, the platform also provides entry links to other functional pages.

<center class="half">
<img src=".\picture\homepage.png" alt="image-homepage"  height="200" />
<img src=".\picture\login.png" alt="image-login" height="200"  />
</center>

2. The products page displays important information about all products, including introduction, prices, and ratings. Users can search for products they want based on adjusting tags and sorting criteria. The search page  also allows users to adjust the search object to get the results they want.

<center class="half">
<img src=".\picture\product.png" alt="image-product" height="250" />
<img src=".\picture\searchPage.png" alt="image-search" height="250"  />
</center>

3. The styles of admin page, customer page and contributor page are similar. For every page, the left part is personal information that can be edited and the right are the special functions of different characters. Customers can view purchased products and products in the shopping cart. Contributors can view and edit their uploaded products, as well as add and edit new products to be uploaded. Administrators can manage user permission applications and products uploaded by contributors. It is worth noting that customers can enter the contributor mode by clicking the creation center button.

<center class="half">
<img src=".\picture\customer.png" alt="image-customer"  height="150" />
<img src=".\picture\contributor.png" alt="image-contributor"  height="150" />
<img src=".\picture\administrator.png" alt="image-administrator"  height="150" />
</center>

4. On the left is the detailed page of the product information that customers can view. The right product page is for contributor to update and edit product.

<center class="half">
<img src=".\picture\productDetail.png" alt="image-productDetail" height="300" />
<img src=".\picture\productForContributor.png" alt="image-productForContributor" height="300" />
</center>

5. We have designed a Q&A forum to realize the interaction of the three: customers, contributors and administrators. 

   <img src=".\picture\Forum.png" alt="image-Forum" style="zoom:40%;" />



#### Requirements Summary

<HR style="FILTER: progid:DXImageTransform.Microsoft.Glow(color=#987cb9,strength=10)" width="80%" color=#987cb9 SIZE=1>

- Contributor:
  - Upload code, executable file in particular form and store it in his/her repository.
  - Manage his/her technologies. Such as: organize folder, make it available or not, update.
  - Test techniques conveniently.
  - Provide programs and services to customer to earn profit.
- Customer: 
  - Use search function to find and get services on the website quickly and accurately.
  - Manage his balance and he can transfer in or withdraw.
  - Manage his favorite program and purchased program.
  - Download the program from the web page.
- Administrator:
  - Test whether the program contributor uploaded runs successfully
  - Provides a repository to store every programs
  - Manage or organize the stored technologies
  - Manage the privilege of Customers and Contributors 
  - Manage members of the Web

#### Design Document

<HR style="FILTER: progid:DXImageTransform.Microsoft.Glow(color=#987cb9,strength=10)" width="80%" color=#987cb9 SIZE=1>

##### Database schema

<img src="C:\Users\hp\AppData\Roaming\Typora\typora-user-images\1603161145731.png" alt="1603161145731" style="zoom: 40%;" />



##### ER diagram

<img src="C:\Users\hp\AppData\Roaming\Typora\typora-user-images\1603161272575.png" alt="1603161272575" style="zoom: 40%;" />

##### Timeline

<img src="C:\Users\hp\AppData\Roaming\Typora\typora-user-images\1603162666786.png" alt="1603162666786" style="zoom:30%;" />



#### Feasibility

<HR style="FILTER: progid:DXImageTransform.Microsoft.Glow(color=#987cb9,strength=10)" width="80%" color=#987cb9 SIZE=1>
Though we have tried our level best to design the system flawless and user-friendly by using modern technologies, some minor functional and design inconsistencies may exist in our order due to time constraints, design of the prototype, and cost constraints. The limitation of SEMojo is:

- The built-in search function of the website is based on the most basic inverted index structure and vector space model, so it may not be able to return perfect results like the Google search engine. However, we will try our best to improve matching accuracy through modifying our evaluation function. 
- We will try our best to test the uploaded programs. But the test function may need users to give some test examples and we can not guarantee the correctness of users' program.
- Consistency between the front end and the back end is also a problem for us. We will try our best to build a system that is as fast as possible, with correct transfers between front end and back end.



#### Useful APIs and Services

<HR style="FILTER: progid:DXImageTransform.Microsoft.Glow(color=#987cb9,strength=10)" width="80%" color=#987cb9 SIZE=1>

Since we hava four roles, visitor, customer, contributor and administrator, we use a common framework, spring security, for authentication and authorization. And we have designed some APIs for each role by refering to https://api.github.com/ and https://docs.github.com/en/free-pro-team@latest/rest : 

- visitor:
  - "code_search_url": "https://semojo.com/search/code?q={query}{&page,per_page,sort,order}"
  - "label_search_url": "https://semojo.com/search/labels?q={query}&repository_id={repository_id}{&page,per_page}"
  - "repository_search_url": "https://semojo.com/search/repositories?q={query}{&page,per_page,sort,order}"
  - "issue_search_url": "https://api.github.com/search/issues?q={query}{&page,per_page,sort,order}"
  - "contributor_search_url": "https://semojo.com/search/contributors?q={query}{&page,per_page,sort,order}"
  - "issues_url": "https://api.github.com/issues"
- custormer:
  - "custormer_url": "https://semojo.com/custormers/{custormer}"
  - "followers_url": "https://semojo.com/custormers/followers"
  - "transaction_url": "https://semojo.com/custormers/transactions"
  - "technique_url": "https://semojo.com/custormers/techniques"
- contributor:
  - "contributor_artifact_url": "https://semojo.com/contributors/{contributor}/repos{?type,page,per_page,sort}"
  - "technique_url": "https://semojo.com/contributors/techniques"
- Administrator
  - "authorizations_url": "https://semojo.com/authorizations"
  - "user_management": "https://semojo.com/user"

Remark: we follow the standard of restful API, so the HTTP verb defines our action. For example, the following is API definition of the openAPI format in swagger:

```
/contributors/{contributorId}/technique/:
    post:
      tags:
      - "contributor"
      summary: "uploads an technique"
      description: ""
      operationId: "uploadFile"
      consumes:
      - "multipart/form-data"
      produces:
      - "application/json"
      parameters:
      - name: "contributorId"
        in: "path"
        description: "ID of contributor who uploads"
        required: true
        type: "integer"
        format: "int64"
      - name: "additionalMetadata"
        in: "formData"
        description: "Additional data to pass to server"
        required: false
        type: "string"
      - name: "file"
        in: "formData"
        description: "file to upload"
        required: false
        type: "file"
      responses:
        "200":
          description: "successful operation"
          schema:
            $ref: "#/definitions/ApiResponse"
      security:
      - petstore_auth:
        - "write:contributors"
```



#### Technologies

<HR style="FILTER: progid:DXImageTransform.Microsoft.Glow(color=#987cb9,strength=10)" width="80%" color=#987cb9 SIZE=1>

FrontEnd: Vue, Element UI, Boostrap

BackEnd: Springboot, Spring Security, Docker
