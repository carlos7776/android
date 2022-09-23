const UsersControlier = require('../controllers/usersController');

module.exports = (app) => {
    
    app.get('/api/users/getAll', usersController.getAll);
}