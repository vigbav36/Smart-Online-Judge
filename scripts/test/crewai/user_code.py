def main():
    
    n = int(input())

    results = []
    for _ in range(n):
        
        number = int(input())
        result = number * 2
        results.append(result)
    
    
    for result in results:
        print(result)

if __name__ == "__main__":
    main()
