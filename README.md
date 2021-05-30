# Satispay Exercise

## Run the script
The script reads a configuration file `config.json` in the `src/main/resources/` folder.  

The json object must have the following required properties: `"host", "resourcePath", "method", "keyId", "body", "algorithm"`.
The **Date** field is injected in the headers, if not specified, and the **Digest** is automatically calculated from the body.

Open the project in IntelliJ IDE and run the `src/main/java/Starter`.

## Result

### GET

config.json
```json
{
  "keyId": "signature-test-66289",
  "algorithm": "rsa-sha256",
  "host": "staging.authservices.satispay.com",
  "resourcePath": "/wally-services/protocol/tests/signature",
  "method": "GET",
  "body": "",
  "headers": {}
}
```

```bash
-----------BEGIN: Construct Signature String-----------
(request-target): get /wally-services/protocol/tests/signature
date: Sun, 30 May 2021 13:31:49 GMT
-----------END: Construct Signature String-----------


-----------BEGIN: Create Signature-----------
nmN3P4lT0iblBMegWIzO78xQFgP/wxLwcX1XaPQWSpmPPIRDchdudcSDLpvbo1SX2MgI6Lqfc3w3GgBqpGgdD9N9UmEJUohusgBT2+LMcicPxoatX5xZ+Obpqr+n5JlFOexiKhspPndNrFi5zuwC7BnXUNIFLBE24xXJe9PYTfNa8VIK6ffoUvrnyttcvCmIWtDWsqadzed1H/XNvtpyOJk9+Z4wm31wDr6XtGsC7DaLp2ZsUugtD9tU6tUhbZ3Q2ggSue3BvEePhN1QSutqGj93k5NGMlEKL6NaHYhQnC6whNeyO2LYVDG9JAhVB7UetL5Tpmr3srv/u4rbS0Qbpw==
-----------END: Create Signature-----------


-----------BEGIN: Get Authorization value-----------
Signature keyId="signature-test-66289",algorithm="rsa-sha256",headers="(request-target) date",signature="nmN3P4lT0iblBMegWIzO78xQFgP/wxLwcX1XaPQWSpmPPIRDchdudcSDLpvbo1SX2MgI6Lqfc3w3GgBqpGgdD9N9UmEJUohusgBT2+LMcicPxoatX5xZ+Obpqr+n5JlFOexiKhspPndNrFi5zuwC7BnXUNIFLBE24xXJe9PYTfNa8VIK6ffoUvrnyttcvCmIWtDWsqadzed1H/XNvtpyOJk9+Z4wm31wDr6XtGsC7DaLp2ZsUugtD9tU6tUhbZ3Q2ggSue3BvEePhN1QSutqGj93k5NGMlEKL6NaHYhQnC6whNeyO2LYVDG9JAhVB7UetL5Tpmr3srv/u4rbS0Qbpw=="
-----------END: Get Authorization value-----------

STATUS CODE 200
{
    "authentication_key": {
        "access_key": "signature-test-66289",
        "auth_type": "SIGNATURE",
        "customer_uid": "00d25aa7-aa6e-4941-b8af-a18d4d9b8db3",
        "enable": true,
        "insert_date": "2017-11-28T15:04:50.000Z",
        "key_type": "RSA_PUBLIC_KEY",
        "role": "DEVICE",
        "sequence": 7,
        "update_date": "2020-04-06T14:45:49.946Z",
        "version": 1
    },
    "signature": {
        "algorithm": "RSA_SHA256",
        "headers": [
            "(request-target)",
            "date"
        ],
        "iteration_count": 2617,
        "key_id": "signature-test-66289",
        "resign_required": false,
        "signature": "nmN3P4lT0iblBMegWIzO78xQFgP/wxLwcX1XaPQWSpmPPIRDchdudcSDLpvbo1SX2MgI6Lqfc3w3GgBqpGgdD9N9UmEJUohusgBT2+LMcicPxoatX5xZ+Obpqr+n5JlFOexiKhspPndNrFi5zuwC7BnXUNIFLBE24xXJe9PYTfNa8VIK6ffoUvrnyttcvCmIWtDWsqadzed1H/XNvtpyOJk9+Z4wm31wDr6XtGsC7DaLp2ZsUugtD9tU6tUhbZ3Q2ggSue3BvEePhN1QSutqGj93k5NGMlEKL6NaHYhQnC6whNeyO2LYVDG9JAhVB7UetL5Tpmr3srv/u4rbS0Qbpw==",
        "valid": true
    },
    "signed_string": "(request-target): get /wally-services/protocol/tests/signature\ndate: Sun, 30 May 2021 13:31:49 GMT"
}
```

NOTE: passing a body in a GET method will not work, the script will inject a digest but will not match in the server (ignored?)

### POST

config.json
```json
{
  "keyId": "signature-test-66289",
  "algorithm": "rsa-sha256",
  "host": "staging.authservices.satispay.com",
  "resourcePath": "/wally-services/protocol/tests/signature",
  "method": "POST",
  "body": "",
  "headers": {}
}
```

```shell
-----------BEGIN: Construct Signature String-----------
(request-target): post /wally-services/protocol/tests/signature
date: Sun, 30 May 2021 13:31:02 GMT
-----------END: Construct Signature String-----------


-----------BEGIN: Create Signature-----------
rGDh1izhf8tksTyFTwCiBd8ATKoWS01F4gSt7diUAs8by/Vj6SVYdEsd7mO1Dcg8NqlVU9XPBiqYFJ8TiJTfndFvg2v2nAcEpcltx1OYP7jQAN9C+FhQ1S621cbRxMNNC1I+rCNQ+NTJ1gP+vlC1BHxjgQO7/VWFiYz4JVnLC+o6v0XYUijWnn/lMoK3axb2fFAzFPpOODzTxU8Oo0XHl+rfiQhwtVzt2ar84HzVGzIBnSf32CbSi9VoQ6nuB+38xp+9X8Re0auFxfFedn2sxkOgV8/SAQdJDbn7dXXaZ9WqbI086K2Ar8trcKpVHrqCqGikuciH+tw9ICJKhtIyCw==
-----------END: Create Signature-----------


-----------BEGIN: Get Authorization value-----------
Signature keyId="signature-test-66289",algorithm="rsa-sha256",headers="(request-target) date",signature="rGDh1izhf8tksTyFTwCiBd8ATKoWS01F4gSt7diUAs8by/Vj6SVYdEsd7mO1Dcg8NqlVU9XPBiqYFJ8TiJTfndFvg2v2nAcEpcltx1OYP7jQAN9C+FhQ1S621cbRxMNNC1I+rCNQ+NTJ1gP+vlC1BHxjgQO7/VWFiYz4JVnLC+o6v0XYUijWnn/lMoK3axb2fFAzFPpOODzTxU8Oo0XHl+rfiQhwtVzt2ar84HzVGzIBnSf32CbSi9VoQ6nuB+38xp+9X8Re0auFxfFedn2sxkOgV8/SAQdJDbn7dXXaZ9WqbI086K2Ar8trcKpVHrqCqGikuciH+tw9ICJKhtIyCw=="
-----------END: Get Authorization value-----------

STATUS CODE 200
{
    "authentication_key": {
        "access_key": "signature-test-66289",
        "auth_type": "SIGNATURE",
        "customer_uid": "00d25aa7-aa6e-4941-b8af-a18d4d9b8db3",
        "enable": true,
        "insert_date": "2017-11-28T15:04:50.000Z",
        "key_type": "RSA_PUBLIC_KEY",
        "role": "DEVICE",
        "sequence": 7,
        "update_date": "2020-04-06T14:45:49.946Z",
        "version": 1
    },
    "signature": {
        "algorithm": "RSA_SHA256",
        "headers": [
            "(request-target)",
            "date"
        ],
        "iteration_count": 2617,
        "key_id": "signature-test-66289",
        "resign_required": false,
        "signature": "rGDh1izhf8tksTyFTwCiBd8ATKoWS01F4gSt7diUAs8by/Vj6SVYdEsd7mO1Dcg8NqlVU9XPBiqYFJ8TiJTfndFvg2v2nAcEpcltx1OYP7jQAN9C+FhQ1S621cbRxMNNC1I+rCNQ+NTJ1gP+vlC1BHxjgQO7/VWFiYz4JVnLC+o6v0XYUijWnn/lMoK3axb2fFAzFPpOODzTxU8Oo0XHl+rfiQhwtVzt2ar84HzVGzIBnSf32CbSi9VoQ6nuB+38xp+9X8Re0auFxfFedn2sxkOgV8/SAQdJDbn7dXXaZ9WqbI086K2Ar8trcKpVHrqCqGikuciH+tw9ICJKhtIyCw==",
        "valid": true
    },
    "signed_string": "(request-target): post /wally-services/protocol/tests/signature\ndate: Sun, 30 May 2021 13:31:02 GMT"
}
```

config.json with body
```json
{
  "keyId": "signature-test-66289",
  "algorithm": "rsa-sha256",
  "host": "staging.authservices.satispay.com",
  "resourcePath": "/wally-services/protocol/tests/signature",
  "method": "POST",
  "body": "ciao",
  "headers": {
    "My-flag": "flag-jin"
  }
}
```

```shell
-----------BEGIN: Construct Signature String-----------
(request-target): post /wally-services/protocol/tests/signature
my-flag: flag-jin
digest: SHA-256=sTOgwOm+474gFj0q0x1iSNspKqbcse4IeiqlDg/HWuI=
date: Sun, 30 May 2021 13:28:45 GMT
-----------END: Construct Signature String-----------


-----------BEGIN: Create Signature-----------
RCVSoMQZWsr6y4gMjGRPgZ4N2NB97RoGGb8iV8fj1N54C9gvuRdzFwnP/KKYQrXY+CwhUEm3yO3keZGLvq9uXux+9ouOo3mNp5SoGZEnbhEx+hGYDDvmS3mqbbYcXb+lnzKGc6tZpPNH0+HDa2AaOlnSboWQRSANyShqG8Xc2MvznfmbTOFM5gWmaxzmiUS5ABU6rYWb6KACm8vOGBwBM1ca9ZrS+98S3cGoadzmazcRUAIGd6ifkyx/JIobOnRLF+rTwSJdBuqzrbY4ZNAYJYz2N3AAe8qvNGNLrcGk1WbwJsrtlS2lKLtXNia7pOV7Mf5cTYatBl6Ig4po08/HwQ==
-----------END: Create Signature-----------


-----------BEGIN: Get Authorization value-----------
Signature keyId="signature-test-66289",algorithm="rsa-sha256",headers="(request-target) my-flag digest date",signature="RCVSoMQZWsr6y4gMjGRPgZ4N2NB97RoGGb8iV8fj1N54C9gvuRdzFwnP/KKYQrXY+CwhUEm3yO3keZGLvq9uXux+9ouOo3mNp5SoGZEnbhEx+hGYDDvmS3mqbbYcXb+lnzKGc6tZpPNH0+HDa2AaOlnSboWQRSANyShqG8Xc2MvznfmbTOFM5gWmaxzmiUS5ABU6rYWb6KACm8vOGBwBM1ca9ZrS+98S3cGoadzmazcRUAIGd6ifkyx/JIobOnRLF+rTwSJdBuqzrbY4ZNAYJYz2N3AAe8qvNGNLrcGk1WbwJsrtlS2lKLtXNia7pOV7Mf5cTYatBl6Ig4po08/HwQ=="
-----------END: Get Authorization value-----------

STATUS CODE 200
{
    "authentication_key": {
        "access_key": "signature-test-66289",
        "auth_type": "SIGNATURE",
        "customer_uid": "00d25aa7-aa6e-4941-b8af-a18d4d9b8db3",
        "enable": true,
        "insert_date": "2017-11-28T15:04:50.000Z",
        "key_type": "RSA_PUBLIC_KEY",
        "role": "DEVICE",
        "sequence": 7,
        "update_date": "2020-04-06T14:45:49.946Z",
        "version": 1
    },
    "signature": {
        "algorithm": "RSA_SHA256",
        "headers": [
            "(request-target)",
            "my-flag",
            "digest",
            "date"
        ],
        "iteration_count": 2617,
        "key_id": "signature-test-66289",
        "resign_required": false,
        "signature": "RCVSoMQZWsr6y4gMjGRPgZ4N2NB97RoGGb8iV8fj1N54C9gvuRdzFwnP/KKYQrXY+CwhUEm3yO3keZGLvq9uXux+9ouOo3mNp5SoGZEnbhEx+hGYDDvmS3mqbbYcXb+lnzKGc6tZpPNH0+HDa2AaOlnSboWQRSANyShqG8Xc2MvznfmbTOFM5gWmaxzmiUS5ABU6rYWb6KACm8vOGBwBM1ca9ZrS+98S3cGoadzmazcRUAIGd6ifkyx/JIobOnRLF+rTwSJdBuqzrbY4ZNAYJYz2N3AAe8qvNGNLrcGk1WbwJsrtlS2lKLtXNia7pOV7Mf5cTYatBl6Ig4po08/HwQ==",
        "valid": true
    },
    "signed_string": "(request-target): post /wally-services/protocol/tests/signature\nmy-flag: flag-jin\ndigest: SHA-256=sTOgwOm+474gFj0q0x1iSNspKqbcse4IeiqlDg/HWuI=\ndate: Sun, 30 May 2021 13:28:45 GMT"
}
```

### DELETE

config.json
```json
{
  "keyId": "signature-test-66289",
  "algorithm": "rsa-sha256",
  "host": "staging.authservices.satispay.com",
  "resourcePath": "/wally-services/protocol/tests/signature",
  "method": "DELETE",
  "body": "",
  "headers": {}
}
```

```shell
{
    "authentication_key": {
        "access_key": "signature-test-66289",
        "auth_type": "SIGNATURE",
        "customer_uid": "00d25aa7-aa6e-4941-b8af-a18d4d9b8db3",
        "enable": true,
        "insert_date": "2017-11-28T15:04:50.000Z",
        "key_type": "RSA_PUBLIC_KEY",
        "role": "DEVICE",
        "sequence": 7,
        "update_date": "2020-04-06T14:45:49.946Z",
        "version": 1
    },
    "signature": {
        "algorithm": "RSA_SHA256",
        "headers": [
            "(request-target)",
            "date"
        ],
        "iteration_count": 2617,
        "key_id": "signature-test-66289",
        "resign_required": false,
        "signature": "oKxQmvh44AtC84SmbAHeb6RbpqqVBwZcTa5Y6bn/cK4/Y2S6JtEZTG+srdEwgrexUJaoaLb2p2bbl2gmVLkV+ZLs0DpJzRljNeaZ0R1+WxwFBBr5yLYzu6JqJfaGwwgVKllam/rEIej6Wpbwd7biACvr2NjJLK/Qo/UNS3kpqJh5TfnjwJVYynAVOn85cRDMexRkqqb+VsID0vp/VJm8b9y9Tv2xtunHk+EuJMTjhS5TaMpA9d2NOneMy4xbONvqGAzkWo9nWzYjY4j6I4+GNZ7HPE6pyVNdxae2hMnEGTCp1JGiR9wtU97H9dDVz9DnG4RZ/o9KaWec5+qpRYCwmg==",
        "valid": true
    },
    "signed_string": "(request-target): delete /wally-services/protocol/tests/signature\ndate: Sun, 30 May 2021 13:36:23 GMT"
}
```

NOTE: passing a body in a DELETE request, I got `400 Bad Request`.
Possible Reason: 
```text
You can't have a HttpMethod.DELETE with a body.

This is not explicitly stated in the RFC, but some Proxy servers will reject the body if you have one in a delete method.
```

### PUT

config.json
```json
{
  "keyId": "signature-test-66289",
  "algorithm": "rsa-sha256",
  "host": "staging.authservices.satispay.com",
  "resourcePath": "/wally-services/protocol/tests/signature",
  "method": "PUT",
  "body": "",
  "headers": {}
}
```

```shell
-----------BEGIN: Construct Signature String-----------
(request-target): put /wally-services/protocol/tests/signature
date: Sun, 30 May 2021 13:43:08 GMT
-----------END: Construct Signature String-----------


-----------BEGIN: Create Signature-----------
Ui4UrXA5+1UqA7VG11jYzr0C75T0B5X8wkXje6tLPkprPMOsOgQT9u7D5s+IvKyAURV7Jj237m6O5UxDewkUJ5U/bM/1YGzoAukgxU25bHYtNRavo0X0b/2GxxpeC7vI/vyTkMX3K9M6bfX9dm/9ZQHYJPZIXaH3XWGfRxa3ltGAqWeXjvHZSM0c8WUznSaZ24e+vU323ci4OseByEIGzKatzoPhXcRyzI8bSiGLtp7UCQLzCgQ0SP3IFq2CfhG5lUQC7zcVa7mK0F6jWGOgtb8ie04X8okTiAxT9iPgtiR2sJRQjDnZ+VwVJJJO0mAqB0BAwnSNHBYmWI77oR3s4Q==
-----------END: Create Signature-----------


-----------BEGIN: Get Authorization value-----------
Signature keyId="signature-test-66289",algorithm="rsa-sha256",headers="(request-target) date",signature="Ui4UrXA5+1UqA7VG11jYzr0C75T0B5X8wkXje6tLPkprPMOsOgQT9u7D5s+IvKyAURV7Jj237m6O5UxDewkUJ5U/bM/1YGzoAukgxU25bHYtNRavo0X0b/2GxxpeC7vI/vyTkMX3K9M6bfX9dm/9ZQHYJPZIXaH3XWGfRxa3ltGAqWeXjvHZSM0c8WUznSaZ24e+vU323ci4OseByEIGzKatzoPhXcRyzI8bSiGLtp7UCQLzCgQ0SP3IFq2CfhG5lUQC7zcVa7mK0F6jWGOgtb8ie04X8okTiAxT9iPgtiR2sJRQjDnZ+VwVJJJO0mAqB0BAwnSNHBYmWI77oR3s4Q=="
-----------END: Get Authorization value-----------

STATUS CODE 200
{
    "authentication_key": {
        "access_key": "signature-test-66289",
        "auth_type": "SIGNATURE",
        "customer_uid": "00d25aa7-aa6e-4941-b8af-a18d4d9b8db3",
        "enable": true,
        "insert_date": "2017-11-28T15:04:50.000Z",
        "key_type": "RSA_PUBLIC_KEY",
        "role": "DEVICE",
        "sequence": 7,
        "update_date": "2020-04-06T14:45:49.946Z",
        "version": 1
    },
    "signature": {
        "algorithm": "RSA_SHA256",
        "headers": [
            "(request-target)",
            "date"
        ],
        "iteration_count": 2617,
        "key_id": "signature-test-66289",
        "resign_required": false,
        "signature": "Ui4UrXA5+1UqA7VG11jYzr0C75T0B5X8wkXje6tLPkprPMOsOgQT9u7D5s+IvKyAURV7Jj237m6O5UxDewkUJ5U/bM/1YGzoAukgxU25bHYtNRavo0X0b/2GxxpeC7vI/vyTkMX3K9M6bfX9dm/9ZQHYJPZIXaH3XWGfRxa3ltGAqWeXjvHZSM0c8WUznSaZ24e+vU323ci4OseByEIGzKatzoPhXcRyzI8bSiGLtp7UCQLzCgQ0SP3IFq2CfhG5lUQC7zcVa7mK0F6jWGOgtb8ie04X8okTiAxT9iPgtiR2sJRQjDnZ+VwVJJJO0mAqB0BAwnSNHBYmWI77oR3s4Q==",
        "valid": true
    },
    "signed_string": "(request-target): put /wally-services/protocol/tests/signature\ndate: Sun, 30 May 2021 13:43:08 GMT"
}
```

config.json with body

```json
{
  "keyId": "signature-test-66289",
  "algorithm": "rsa-sha256",
  "host": "staging.authservices.satispay.com",
  "resourcePath": "/wally-services/protocol/tests/signature",
  "method": "PUT",
  "body": "put me there",
  "headers": {}
}
```

```shell
-----------BEGIN: Construct Signature String-----------
(request-target): put /wally-services/protocol/tests/signature
digest: SHA-256=n5t1fp0rNT5tp17V4WwXl/ExckyzomQtc4ZH3AjJrT0=
date: Sun, 30 May 2021 13:43:48 GMT
-----------END: Construct Signature String-----------


-----------BEGIN: Create Signature-----------
jb6u8O3HYZaQHad1iETnAhoM5AV8+J/KQ4JUBwD2eQL64AaiV0NDRLSiuW/paTi6iHLGsCbFnpjRGlKtb72YG7YTY2bbPEe5XGFY+WikNCt3fe4EtAmtsT30Z/pVdrg+JKyH06QDbabKBmmE+AWiueXbjfzKojaUPx3zO1f2mR3lAsas7aOxAFc8l9RksfTvuPiEjXhn+WY4EKkrXULexitKNf345YusqdNPZnzf4sTQiXfnvhL6FoU8+GdYtt0rhgG8e/fJbNtz7llVHaIgV7PLBzznr4N9q7PBLyd94sP3KLN/3WsDwAvI4G9l/AhLxM9Foj7q/onCqhuFnBexsA==
-----------END: Create Signature-----------


-----------BEGIN: Get Authorization value-----------
Signature keyId="signature-test-66289",algorithm="rsa-sha256",headers="(request-target) digest date",signature="jb6u8O3HYZaQHad1iETnAhoM5AV8+J/KQ4JUBwD2eQL64AaiV0NDRLSiuW/paTi6iHLGsCbFnpjRGlKtb72YG7YTY2bbPEe5XGFY+WikNCt3fe4EtAmtsT30Z/pVdrg+JKyH06QDbabKBmmE+AWiueXbjfzKojaUPx3zO1f2mR3lAsas7aOxAFc8l9RksfTvuPiEjXhn+WY4EKkrXULexitKNf345YusqdNPZnzf4sTQiXfnvhL6FoU8+GdYtt0rhgG8e/fJbNtz7llVHaIgV7PLBzznr4N9q7PBLyd94sP3KLN/3WsDwAvI4G9l/AhLxM9Foj7q/onCqhuFnBexsA=="
-----------END: Get Authorization value-----------

STATUS CODE 200
{
    "authentication_key": {
        "access_key": "signature-test-66289",
        "auth_type": "SIGNATURE",
        "customer_uid": "00d25aa7-aa6e-4941-b8af-a18d4d9b8db3",
        "enable": true,
        "insert_date": "2017-11-28T15:04:50.000Z",
        "key_type": "RSA_PUBLIC_KEY",
        "role": "DEVICE",
        "sequence": 7,
        "update_date": "2020-04-06T14:45:49.946Z",
        "version": 1
    },
    "signature": {
        "algorithm": "RSA_SHA256",
        "headers": [
            "(request-target)",
            "digest",
            "date"
        ],
        "iteration_count": 2617,
        "key_id": "signature-test-66289",
        "resign_required": false,
        "signature": "jb6u8O3HYZaQHad1iETnAhoM5AV8+J/KQ4JUBwD2eQL64AaiV0NDRLSiuW/paTi6iHLGsCbFnpjRGlKtb72YG7YTY2bbPEe5XGFY+WikNCt3fe4EtAmtsT30Z/pVdrg+JKyH06QDbabKBmmE+AWiueXbjfzKojaUPx3zO1f2mR3lAsas7aOxAFc8l9RksfTvuPiEjXhn+WY4EKkrXULexitKNf345YusqdNPZnzf4sTQiXfnvhL6FoU8+GdYtt0rhgG8e/fJbNtz7llVHaIgV7PLBzznr4N9q7PBLyd94sP3KLN/3WsDwAvI4G9l/AhLxM9Foj7q/onCqhuFnBexsA==",
        "valid": true
    },
    "signed_string": "(request-target): put /wally-services/protocol/tests/signature\ndigest: SHA-256=n5t1fp0rNT5tp17V4WwXl/ExckyzomQtc4ZH3AjJrT0=\ndate: Sun, 30 May 2021 13:43:48 GMT"
}
```