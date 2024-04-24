#### Using coroutineScope

Imagine that in some repository function you need to asynchronously load two resources, for example user data and a list of articles. 
In this case, you want to return only those articles that should be seen by the user. 
To call async, we need a scope, but we don’t want to pass it to a function24. 
To create a scope out of a suspending function, we use the coroutineScope function.

```
suspend fun getArticlesForUser( userToken: String?,
): List<ArticleJson> = coroutineScope {
    val articles = async { articleRepository.getArticles() } 
    val user = userService.getUser(userToken) articles.await()
       .filter { canSeeOnList(user, it) }
       .map { toArticleJson(it) }
}
```

> coroutineScope is just a suspending function that creates a scope for its lambda expression. 
> The function returns whatever is returned by the lambda expression (like let, run, use, or runBlocking). 
> So, in the above example, it returns List<ArticleJson> because this is what is returned from the lambda expression.
coroutineScope is a standard function we use when we need a scope inside a suspending function.



#### runBlocking vs coroutineScope

> runBlocking is not a suspend fun. The thread that called it remains inside it until the coroutine is complete.

> coroutineScope is a suspend fun. If your coroutine suspends, the coroutineScope function gets suspended as well. 
> This allows the top-level function, a non-suspending function that created the coroutine, to continue executing on the same thread. The thread has "escaped" the coroutineScope block and is ready to do some other work.

As soon as you enter the body of runBlocking, you are in the suspendable world, where suspend functions behave like blocking code 
and you can't get to the next line until the suspend fun returns. 
coroutineScope is a suspend fun that returns only when all the coroutines inside it are done. 

##### The only net effect is that coroutineScope can be cancelled. 
