app.config(function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/');

    $stateProvider
        .state('home', {
            url: '/',
            templateUrl: 'assets/views/main.html',
            controller: 'userController'
        })
        .state('registration', {
            url: "/registration",
            templateUrl: "assets/views/registration.html",
            controller: 'userController'
        })
        .state('add_author', {
            url: "/add_author",
            templateUrl: "assets/views/add_author.html",
            controller: 'userController'
        })
        .state('author', {
            url: "/author",
            templateUrl: "assets/views/author.html",
            controller: 'userController'
        })
        .state('authors', {
            url: "/authors",
            templateUrl: "assets/views/authors.html",
            controller: 'authorsController'
        })
        .state('add_book', {
            url: "/authors/:author_id/books/new",
            templateUrl: "assets/views/add_book.html",
            controller: 'authorsController'
        })
        .state('books', {
            url: "/books",
            templateUrl: "assets/views/books.html",
            controller: 'booksController'
        })
        .state('bookView', {
            url: "/books/:id",
            templateUrl: "assets/views/book_view.html",
            controller: 'booksDetailController'
        })
        .state('edit_author', {
            url: "/authors/:author_id/edit",
            templateUrl: "assets/views/edit_author.html",
            controller: 'authorsController'
        })
        .state('edit_book', {
            url: "/book/:id/edit",
            templateUrl: "assets/views/edit_book.html",
            controller: 'booksDetailController'
        })
        .state('login', {
            url: "/login",
            templateUrl: "assets/views/login.html",
            controller: 'userController'
        })
        .state('user', {
            url: "/users/:id",
            templateUrl: "assets/views/user.html",
            controller: 'usersDetailController'
        })
        .state('users', {
            url: "/users",
            templateUrl: "assets/views/users.html",
            controller: 'userController'
        })
});