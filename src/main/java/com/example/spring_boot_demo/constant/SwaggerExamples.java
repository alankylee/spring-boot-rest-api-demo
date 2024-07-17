package com.example.spring_boot_demo.constant;

public final class SwaggerExamples {

    private SwaggerExamples() {} // Prevents instantiation

    public static final class AuthenticationExamples {

        private AuthenticationExamples() {} // Prevents instantiation

        public static final String REGISTER_SUCCESS_RESPONSE = """
        {
          "success": true,
          "message": "Registration successful",
          "data": null
        }
        """;

        public static final String REGISTER_ERROR_RESPONSE = """
        {
          "success": false,
          "message": "That email is taken. Try another.",
          "data": null
        }
        """;

        public static final String LOGIN_SUCCESS_RESPONSE = """
        {
          "success": true,
          "message": "Login successful",
          "data": {
            "accessToken": "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJteW5hbWVAZW1haWwuY29tIiwiaWF0IjoxNzIxMTA4Mzk3LCJleHAiOjE3MjExMTE5OTd9.dnwnFiAscoK1jZv8hMSJTx8o83ljPJ4484UrF07jrChk6vo-XMznA5yXS3NnvVg_",
            "tokenType": "Bearer"
          }
        }
        """;

        public static final String LOGIN_ERROR_RESPONSE = """
        {
          "success": false,
          "message": "Bad credentials",
          "data": null
        }
        """;
    }

    public static final class HealthCheckExamples {

        private HealthCheckExamples() {} // Prevents instantiation

        public static final String CHECK_SUCCESS_RESPONSE = """
        {
          "success": true,
          "message": "It works!",
          "data": null
        }
        """;
    }
    
    public static final class TestDataExamples {

        private TestDataExamples() {} // Prevents instantiation

        public static final String FIND_SUCCESS_RESPONSE = """
        {
          "success": true,
          "message": "Success.",
          "data": {
            "id": 1,
            "number": 2147483647,
            "pet": "Cat",
            "gender": "M",
            "birthday": "2024-07-01"
          }
        }
        """;

        public static final String FIND_ALL_SUCCESS_RESPONSE = """
        {
          "success": true,
          "message": "Success.",
          "data": [
            {
              "id": 1,
              "number": 2147483647,
              "pet": "Cat",
              "gender": "M",
              "birthday": "2024-07-01"
            },
            {
              "id": 2,
              "number": 2147483647,
              "pet": "Cat",
              "gender": "M",
              "birthday": "2024-07-01"
            },
            {
              "id": 3,
              "number": 2147483647,
              "pet": "Cat",
              "gender": "M",
              "birthday": "2024-07-01"
            }
          ]
        }
        """;

        public static final String DOWNLOAD_SUCCESS_RESPONSE = """
        Download file
        """;

        public static final String CREATE_SUCCESS_RESPONSE = """
        {
          "success": true,
          "message": "Craeted.",
          "data": null
        }
        """;

        public static final String UPDATE_SUCCESS_RESPONSE = """
        {
          "success": true,
          "message": "Updated.",
          "data": null
        }
        """;

        public static final String DELETE_SUCCESS_RESPONSE = """
        {
          "success": true,
          "message": "Deleted.",
          "data": null
        }
        """;

        public static final String DELETE_ALL_SUCCESS_RESPONSE = """
        {
          "success": true,
          "message": "Deleted all.",
          "data": null
        }
        """;

    }

}
