# Satispay Exercise

## Run the server locally
Open the project in IntelliJ IDE and run the `src/main/java/Server`.
This server accepts only one single connection.

Then connect to the server
```bash
nc 127.0.0.1 8080
```


curl --location --request POST 'https://staging.authservices.satispay.com/wally-services/protocol/tests/signature' \
--header 'accept: application/json' \
--header 'authorization: Signature keyId="vmkhqr4c41u9p2mv2sr0l35a6r0eb3v0uqffgd5cptcr7nm1b0pe9t8v2hobeuic8afbomhgfhgfbbv6ntv1or5q0n8cc5pe55ggtvnn9e4mkcdpkmencvmu14fvs18ov8id9e4suamrj9in9kndlln872kojqcmdtdpp8vmt2cgri4in6msejbp27rc07d54aandube", algorithm="rsa-sha256", headers="(request-target) host date digest", signature="lLc4Z1F47UJomL1OtXKP2+zbPNMi9LxmCtlCBblZ33r8xlNXfjRJdiOIcW/ADb2AVf0YH5tk1Ww4Brk2gUgl1dS3kj8HqdM/A6yUm2cCY6G2c2oncRRy/nV05qWom0FGpZtJnE2PtX9xKU4qbuYw/itm0o2OMhxPqvqSKVDEdylCPOYN2YBx3F/YMxuahNbUY7uxJ/g7R6lO6R96rjNxMlj+lmpRJ6S9ylRKXKU0cKAa3PRNSLEOJToZ9Ng3UdtpCSCBxXjW4nR/StIpAbnzGW8o9sA9vv+IhJpQqO290DiAGhlD9g2EDrl8OJ6CI2n+At21s81ThnMCjxn6UiBAlBx4j//8pTOBRSca924I4UcvxAXW/wZPQcMGSDHSyZBAAaqRzCGTnL5knWUk9FdJGLjz5nh4ZZiNnkZDcneAPQw/nFbAbx4uHTbJjH3KEkEXmG+Utv0yBkaouA+ku4T8lseyL0M2YFO5NDzcO15MeRVRrLBP73jwOtjObzdi6t8+LXHzExitMEnRONVCobTVg1MSoXZuBr6rV5Oj2EhWNxkm5QntLNjznxHv4tklYN4qYUj0A+XKAZWDGAQ0q9Os69RVbE5i4huIwvLnwC1T96PF2o0S+lM+SvoqVtODjFRjEJVE0p8JDv57R7TVFy8vvDB/cI5pQZEtFaNLRK6S61A="' \
--header 'content-length: 61' \
--header 'content-type: application/json; charset=utf-8' \
--header 'date: Tue, 05 May 2020 19:49:05 +0000' \
--header 'digest: SHA-256=a5UF/fcWo+KdzPGADk9XDV/CwKsGyrNLNKGind53oVM=' \
--header 'host: staging.authservices.satispay.com' \
--header 'request-id: |28b6be4c-4db6cd9f682ee36c.1.' \
--header 'Content-Type: text/plain' \
--data-raw '{"flow": "MATCH_CODE", "amount_unit": 100, "currency": "EUR"}'


Signature keyId="signature-test-66289",algorithm="rsa-sha256",headers="(request-target) date", signature="SqfxsyDZWSuxxZyuJnuHBNB3nWUqmBLH43gEwGzuVnFEw/D5Tu5nkETvL9/iGJR+7gTED2lUujhUiCp7ScQ9LeT6kTorkJgicLuFgCvFwjyu3M8731lVMcNoDpJabSiFai+i8JumDSd7Qsl9F1JoQhuTw5Dnj/ocxZuP29aK0/xpLKyITj8FLSVudgFv/kZ3cGWRTF0Bzxh39iooN2Njp6JQq4v9xPe4ZIhXEk3Mcdl2LLPIKuFqCJmz0UvvuXksRJKrIDNQ6MLz4sfc69sY0h7Yi6/ffpenVKTtdSqQmd8BCEWXFgmgZCVc1m1i098znfXXSN72QbuGQryFB4lPBQ=="