package com.example.demo;

import com.example.demo.commands.AddRatingCommand;
import com.example.demo.commands.CommandKeyword;
import com.example.demo.commands.CommandRegistry;
import com.example.demo.commands.DescribeRestaurantCommand;
import com.example.demo.commands.GetReviewsCommand;
import com.example.demo.commands.GetReviewsFilterOrderCommand;
import com.example.demo.commands.ListRestaurantCommand;
import com.example.demo.commands.RegisterRestaurantCommand;
import com.example.demo.commands.RegisterUserCommand;
import com.example.demo.repositories.RestaurantRepository;
import com.example.demo.repositories.ReviewRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.RestaurantService;
import com.example.demo.services.ReviewService;
import com.example.demo.services.UserService;

public class Configuration {
        // Singleton Pattern
        // create an object of Single Configuration Object
        private static Configuration instance = new Configuration();

        // make the constructor private so that this class cannot be
        // instantiated
        private Configuration() {}

        // Get the only object available
        public static Configuration getInstance() {
                return instance;
        }

        // Initialize repositories
        private final UserRepository userRepository = new UserRepository();
        private final RestaurantRepository restaurantRepository = new RestaurantRepository();
        private final ReviewRepository reviewRepository = new ReviewRepository();

        // Initialize services
        private final UserService userService = new UserService(userRepository);
        private final RestaurantService restaurantService =
                        new RestaurantService(restaurantRepository);
        private final ReviewService reviewService =
                        new ReviewService(reviewRepository, userService, restaurantService);

        // Initialize commands
        private final RegisterUserCommand registerUserCommand =
                        new RegisterUserCommand(userService);
        private final RegisterRestaurantCommand registerRestaurantCommand =
                        new RegisterRestaurantCommand(restaurantService);
        private final AddRatingCommand addRatingCommand = new AddRatingCommand(reviewService);
        private final GetReviewsCommand getReviewsCommand = new GetReviewsCommand(reviewService);
        private final GetReviewsFilterOrderCommand getReviewsFilterOrderCommand =
                        new GetReviewsFilterOrderCommand(reviewService);
        private final DescribeRestaurantCommand describeRestaurantCommand =
                        new DescribeRestaurantCommand(restaurantService);
        private final ListRestaurantCommand listRestaurantCommand =
                        new ListRestaurantCommand(restaurantService);

        // Initialize commandRegistery
        private final CommandRegistry commandRegistry = new CommandRegistry();

        // Register commands
        private void registerCommands() {
                commandRegistry.registerCommand(CommandKeyword.REGISTER_USER.getName(),
                                registerUserCommand);
                commandRegistry.registerCommand(CommandKeyword.REGISTER_RESTAURANT.getName(),
                                registerRestaurantCommand);
                commandRegistry.registerCommand(CommandKeyword.ADD_RATING.getName(),
                                addRatingCommand);
                commandRegistry.registerCommand(CommandKeyword.GET_REVIEWS.getName(),
                                getReviewsCommand);
                commandRegistry.registerCommand(CommandKeyword.GET_REVIEWS_FILTER_ORDER.getName(),
                                getReviewsFilterOrderCommand);
                commandRegistry.registerCommand(CommandKeyword.DESCRIBE_RESTAURANT.getName(),
                                describeRestaurantCommand);
                commandRegistry.registerCommand(CommandKeyword.LIST_RESTAURANT.getName(),
                                listRestaurantCommand);
        }

        public CommandRegistry getCommandRegistry() {
                registerCommands();
                return commandRegistry;
        }
}
