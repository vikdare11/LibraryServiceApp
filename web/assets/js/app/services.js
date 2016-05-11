app.factory('AuthService', function($http, $q){
    return {
        login: function(user){
            var deferred = $q.defer();
            $http({
                method: 'POST',
                url: '/login_user',
                data: {user: user},
                headers: {'Content-Type': 'application/json; charset=utf-8'}
            }).success(function (data, status, headers, config) {
                if(data.user){
                    deferred.resolve(data.user);
                } else {
                    deferred.resolve(false);
                }

            }).error(function (data, status, headers, config) {
                deferred.reject(status);
            });

            return deferred.promise;
        },
        check: function(user){
            var deferred = $q.defer();
            $http({
                method: 'GET',
                url: '/check_user_logged',
                data: {user: user},
                headers: {'Content-Type': 'application/json; charset=utf-8'}
            }).success(function (data, status, headers, config) {
                if(data.user){
                    deferred.resolve(data.user);
                } else {
                    deferred.resolve(false);
                }

            }).error(function (data, status, headers, config) {
                deferred.reject(status);
            });

            return deferred.promise;
        },
        logout: function(){
            var deferred = $q.defer();
            $http({
                method: 'GET',
                url: '/user_logout',
                headers: {'Content-Type': 'application/json; charset=utf-8'}
            }).success(function (data, status, headers, config) {
                if(data.user){
                    deferred.resolve(true);
                } else {
                    deferred.resolve(false);
                }

            }).error(function (data, status, headers, config) {
                deferred.reject(status);
            });

            return deferred.promise;
        },
        registerUser: function(user){
            var deferred = $q.defer();
            $http({
                method: 'POST',
                url: '/registrate_user',
                data: {registration: user},
                headers: {'Content-Type': 'application/json; charset=utf-8'}
            }).success(function (data, status, headers, config) {
                deferred.resolve(data.registration);
            }).error(function (data, status, headers, config) {
                deferred.reject(status);
            });

            return deferred.promise;
        }

    }});

app.factory('BooksService', function($http, $q){
   return {
       getAllBooks: function(){
           var deferred = $q.defer();
           $http({
               method: 'GET',
               url: '/get_all_books'
           }).success(function (data, status, headers, config) {
               deferred.resolve(data.books);
           }).error(function (data, status, headers, config) {
               deferred.reject(status);
           });

           return deferred.promise;
       },
       getBook: function(id){
           var deferred = $q.defer();
           $http({
               method: 'POST',
               url: '/view_book_json',
               data:{book:{id: id}},
               headers: {'Content-Type': 'application/json; charset=utf-8'}
           }).success(function (data, status, headers, config) {
               deferred.resolve(data);
           }).error(function (data, status, headers, config) {
               deferred.reject(status);
           });

           return deferred.promise;
       },
       editBook: function(book){
           var deferred = $q.defer();
           $http({
               method: 'POST',
               url: '/edit_book_json',
               data: {book: book},
               headers: {'Content-Type': 'application/json; charset=utf-8'}
           }).success(function (data, status, headers, config) {
               deferred.resolve(data);
           }).error(function (data, status, headers, config) {
               deferred.reject(status);
           });

           return deferred.promise;
       },
       addBookComment: function(comment){
           var deferred = $q.defer();
           $http({
               method: 'POST',
               url: '/add_book_comment',
               data: {comment: comment},
               headers: {'Content-Type': 'application/json; charset=utf-8'}
           }).success(function (data, status, headers, config) {
               deferred.resolve(data);
           }).error(function (data, status, headers, config) {
               alert('Comment adding failed');
               deferred.reject(status);
           });

           return deferred.promise;
       }
   }
});

app.factory('UserService', function($http, $q, $log){
    return {
        getAllUsers: function(){
            var deferred = $q.defer();
            $http({
                method: 'GET',
                url: '/get_all_users'
            }).success(function (data, status, headers, config) {
                deferred.resolve(data.users);
                $log.log(data);
            }).error(function (data, status, headers, config) {
                deferred.reject(status);
                $log.log(data);
            });

            return deferred.promise;
        },
        getUser: function(user_id){
            var deferred = $q.defer();
            $http({
                method: 'POST',
                url: '/get_user_info',
                data: {user: {id: user_id}},
                headers: {'Content-Type': 'application/json; charset=utf-8'}
            }).success(function (data, status, headers, config) {
                deferred.resolve(data);
            }).error(function (data, status, headers, config) {
                alert('Comment adding failed');
                deferred.reject(status);
            });

            return deferred.promise;
        }
    };
});

app.factory('AuthorsService', function($http, $q){
    return {
        getAuthors: function(){
            var deferred = $q.defer();
            $http({
                method: 'GET',
                url: '/get_all_authors'
            }).success(function (data, status, headers, config) {
                deferred.resolve(data.authors);
            }).error(function (data, status, headers, config) {
                deferred.reject(status);
            });

            return deferred.promise;
        },
        addBook: function(book){
            var deferred = $q.defer();
            $http({
                method: 'POST',
                url: '/add_author_book',
                data: {book: book},
                headers: {'Content-Type': 'application/json; charset=utf-8'}
            }).success(function (data, status, headers, config) {
                deferred.resolve(data);
            }).error(function (data, status, headers, config) {
                alert('Book adding failed');
                deferred.reject(status);
            });

            return deferred.promise;
        },
        editAuthor: function(author){
            var deferred = $q.defer();
            $http({
                method: 'POST',
                url: '/edit_author_json',
                data: {author: author},
                headers: {'Content-Type': 'application/json; charset=utf-8'}
            }).success(function (data, status, headers, config) {
                deferred.resolve(data.author);
            }).error(function (data, status, headers, config) {
                alert('Author editing failed');
                deferred.reject(status);
            });

            return deferred.promise;
        },
        deleteAuthor: function(author){
            var deferred = $q.defer();
            $http({
                method: 'POST',
                url: '/delete_author_json',
                data: {author: author},
                headers: {'Content-Type': 'application/json; charset=utf-8'}
            }).success(function (data, status, headers, config) {
                deferred.resolve(data.author);
            }).error(function (data, status, headers, config) {
                alert('Author editing failed');
                deferred.reject(status);
            });

            return deferred.promise;
        }
    };

});