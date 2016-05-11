app.controller('userController', userController);

function userController($scope, $rootScope,  $http, $log,$location,  UserService, AuthService) {
    $scope.allUsers = function(){
        var promiseObj = UserService.getAllUsers();
        promiseObj.then(function(value) {
            if(value){
                $scope.users = value;
            }
        });
    }
    $scope.allUsers();
    $scope.register = function(user){
        var promiseObj = AuthService.registerUser(user);
        promiseObj.then(function(value) {
            if(value){
                alert('Registered');
                $location.url('/login')
            } else {
                alert('Not registered');
            }
        });
    }

    $scope.register = function(user){
        var promiseObj = AuthService.registerUser(user);
        promiseObj.then(function(value) {
            if(value){
                alert('Registered');
                $location.url('/login')
            } else {
                alert('Not registered');
            }
        });
    }
    $scope.login =  function(user){
        $log.log(user);
        var promiseObj = AuthService.login(user);
        promiseObj.then(function(value) {
            if(value){
                $rootScope.logged = true;
                $rootScope.user = value;
                $location.url('/');
            } else {
                alert("Wrong credentials");
            }
        });
    }
    $scope.check =  function(user){
        var promiseObj = AuthService.check(user);
        promiseObj.then(function(value) {
            if(value){
                $rootScope.logged = true;
                $rootScope.user = value;
                $rootScope.logout = $scope.logout;
                $log.log(value);
                alert($rootScope.user.id);
            } else {
                $rootScope.logged = false;
            }
        });
    }

    $scope.logout = function(){
        var promiseObj = AuthService.logout();
        promiseObj.then(function(value) {
            if(value){
                alert("Logout success");
                $rootScope.user = null;
                $rootScope.logged = false;
            } else {
                alert("Logout failed");
            }
        });
    }

    $scope.check();

    $scope.callService = function(argument){
        var myVar = AuthService.checkUser(argument);
        alert(myVar);
    }
}

app.controller('booksController', booksController);

function booksController($scope, $rootScope, BooksService){
    $scope.user = $rootScope.user;
    $scope.getAllBooks = function(){
        var promiseObj = BooksService.getAllBooks();
        promiseObj.then(function(value) {
            if(value){
                $scope.books = value;
            }
        });
    }
    $scope.getAllBooks();
}

app.controller('booksDetailController', booksDetailController);

function booksDetailController($scope,$log,$state,$rootScope,  $location, $stateParams, BooksService){
    var id = $stateParams.id;
    $scope.showBook = function(book_id){
        var promiseObj = BooksService.getBook(book_id);
        promiseObj.then(function(value) {
            if(value){
                $log.log(value);
                $scope.book = value.bookView;
            } else {
                alert('Failed');
            }
        });
    }

    $scope.editBook = function(book){
        var promiseObj = BooksService.editBook(book);
        promiseObj.then(function(value) {
            if(value){
                $location.url('/books/' + book.id);
            } else {
                alert('Failed');
            }
        });
    }
    $scope.addComment = function(){
        var comment = {};
        if(!$rootScope.user){
            alert('You cannot add comments')
            return;
        } else {
            comment.idBook = id;
            comment.idReader = $rootScope.user.id;
            comment.review = $scope.review;
            comment.user = $rootScope.user.name;
        }
        var promiseObj = BooksService.addBookComment(comment);
        promiseObj.then(function(value) {
            if(value){
                $state.reload();
                alert('Comment adding success');
            } else {
                alert('Failed');
            }
        });
    }
    $scope.showBook(id);
}
app.controller('usersDetailController', usersDetailController);

function usersDetailController($scope,$stateParams, $rootScope, $log, UserService, AuthService){
    $scope.check =  function(user){
        var promiseObj = AuthService.check(user);
        promiseObj.then(function(value) {
            if(value){
                $rootScope.logged = true;
                $rootScope.user = value;
                $rootScope.logout = $scope.logout;
                $log.log(value);
            } else {
                $rootScope.logged = false;
            }
        });
    }
    $scope.check();
    $scope.user = $rootScope.user;
    var id = $stateParams.id;

    $scope.showUser = function(id){
        var promiseObj = UserService.getUser(id);
        promiseObj.then(function(value) {
            if(value){
                $log.log(value);
                $scope.login = value.userView.login;
                $scope.email = value.userView.email;
                $scope.books = value.userView.bookCollection;
            } else {
                alert('Failed');
            }
        });
    }
    $scope.showUser(id);

}



app.controller('authorsController', authorsController);

function authorsController($scope, $rootScope,$state,  $stateParams, $log,$location, AuthorsService){
    $scope.author = $stateParams.author;

    $scope.getAuthors = function(){
        var promiseObj = AuthorsService.getAuthors();
        promiseObj.then(function(value) {
            if(value){
                $log.log(value);
                $scope.authors = value;
            } else {
                alert("Authors getting failed");
            }
        });
    }

    $scope.addBook = function(){
        var book = {};

        book.idAuthor = $stateParams.author_id;
        book.name = $scope.title;
        book.description = $scope.description;

        var promiseObj = AuthorsService.addBook(book);
        promiseObj.then(function(value) {
            if(value){
                alert("book added");
                $location.url('/authors')
            } else {
                alert("Authors getting failed");
            }
        });
    }

    $scope.storeAuthor = function(author){
        $scope.author = author;
        $state.go('edit_author', { author_id: author.id, author: author });
        $scope.author = author;
    }

    $scope.editAuthor = function(){
        var promiseObj = AuthorsService.editAuthor($scope.author);
        promiseObj.then(function(value) {
            if(value){
                alert("Author updated");
                $location.url('/authors')
            } else {
                alert("Authors getting failed");
            }
        });
    }

    $scope.deleteAuthor = function(author){
        var promiseObj = AuthorsService.deleteAuthor(author);
        promiseObj.then(function(value) {
            if(value){
                alert("Author deleted");
                $state.reload();
            } else {
                alert("Authors getting failed");
            }
        });
    }
    $scope.getAuthors();
}