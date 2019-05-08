from django.shortcuts import get_object_or_404
from rest_framework import status
from rest_framework.decorators import APIView, api_view
from rest_framework.response import Response
from rest_framework import generics
from django_filters import rest_framework as filters
from django.http import Http404
from .filters import LimitFilterBackend

from .serializers import (
    FeatureSerializer, PlatformSerializer, LanguageSerializer, 
    GenreSerializer, CompanySerializer, GameListSerializer, 
    GameDetailsSerializer
)
from games.models import (
    Feature, Platform, Language, Genre, Company, Game
)
from games.api.filters import GameFilter



# --------------------------------------------------------------------
# Feature API views
# --------------------------------------------------------------------
class FeatureList(APIView):
    """
    List all features, or create a new one.
    """
    def get(self, request, format=None):
        features = Feature.objects.all()
        serializer = FeatureSerializer(features, many=True)
        return Response(serializer.data)

    def post(self, request, format=None):
        serializer = FeatureSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

class FeatureDetail(APIView):
    """
    Retrieve, update or delete a feature instance.
    """
    def get_object(self, pk):
        try:
            return Feature.objects.get(pk=pk)
        except Feature.DoesNotExist:
            raise Http404

    def get(self, request, pk, format=None):
        feature = self.get_object(pk)
        serializer = FeatureSerializer(feature)
        return Response(serializer.data)

    def put(self, request, pk, format=None):
        feature = self.get_object(pk)
        serializer = FeatureSerializer(feature, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, pk, format=None):
        feature = self.get_object(pk)
        feature.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)


# --------------------------------------------------------------------
# Platform API views
# --------------------------------------------------------------------
class PlatformList(APIView):
    """
    List all platforms, or create a new one.
    """
    def get(self, request, format=None):
        platforms = Platform.objects.all()
        serializer = PlatformSerializer(platforms, many=True)
        return Response(serializer.data)

    def post(self, request, format=None):
        serializer = PlatformSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

class PlatformDetail(APIView):
    """
    Retrieve, update or delete a platform instance.
    """
    def get_object(self, pk):
        try:
            return Platform.objects.get(pk=pk)
        except Platform.DoesNotExist:
            raise Http404

    def get(self, request, pk, format=None):
        platform = self.get_object(pk)
        serializer = PlatformSerializer(platform)
        return Response(serializer.data)

    def put(self, request, pk, format=None):
        platform = self.get_object(pk)
        serializer = PlatformSerializer(platform, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, pk, format=None):
        platform = self.get_object(pk)
        platform.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)


# --------------------------------------------------------------------
# Language API views
# --------------------------------------------------------------------
class LanguageList(APIView):
    """
    List all languages, or create a new one.
    """
    def get(self, request, format=None):
        languages = Language.objects.all()
        serializer = LanguageSerializer(languages, many=True)
        return Response(serializer.data)

    def post(self, request, format=None):
        serializer = LanguageSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

class LanguageDetail(APIView):
    """
    Retrieve, update or delete a language instance.
    """
    def get_object(self, pk):
        try:
            return Language.objects.get(pk=pk)
        except Language.DoesNotExist:
            raise Http404

    def get(self, request, pk, format=None):
        language = self.get_object(pk)
        serializer = LanguageSerializer(language)
        return Response(serializer.data)

    def put(self, request, pk, format=None):
        language = self.get_object(pk)
        serializer = LanguageSerializer(language, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, pk, format=None):
        language = self.get_object(pk)
        language.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)


# --------------------------------------------------------------------
# Genre API views
# --------------------------------------------------------------------
class GenreList(APIView):
    """
    List all genres, or create a new one.
    """
    def get(self, request, format=None):
        genres = Genre.objects.all()
        serializer = GenreSerializer(genres, many=True)
        return Response(serializer.data)

    def post(self, request, format=None):
        serializer = GenreSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

class GenreDetail(APIView):
    """
    Retrieve, update or delete a genre instance.
    """
    def get_object(self, pk):
        try:
            return Genre.objects.get(pk=pk)
        except Genre.DoesNotExist:
            raise Http404

    def get(self, request, pk, format=None):
        genre = self.get_object(pk)
        serializer = GenreSerializer(genre)
        return Response(serializer.data)

    def put(self, request, pk, format=None):
        genre = self.get_object(pk)
        serializer = GenreSerializer(genre, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, pk, format=None):
        genre = self.get_object(pk)
        genre.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)


# --------------------------------------------------------------------
# Company API views
# --------------------------------------------------------------------
class CompanyList(APIView):
    """
    List all companies, or create a new one.
    """
    def get(self, request, format=None):
        companies = Company.objects.all()
        serializer = CompanySerializer(companies, many=True)
        return Response(serializer.data)

    def post(self, request, format=None):
        serializer = CompanySerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

class CompanyDetail(APIView):
    """
    Retrieve, update or delete a company instance.
    """
    def get_object(self, pk):
        try:
            return Company.objects.get(pk=pk)
        except Company.DoesNotExist:
            raise Http404

    def get(self, request, pk, format=None):
        company = self.get_object(pk)
        serializer = CompanySerializer(company)
        return Response(serializer.data)

    def put(self, request, pk, format=None):
        company = self.get_object(pk)
        serializer = CompanySerializer(company, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, pk, format=None):
        company = self.get_object(pk)
        company.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)


# --------------------------------------------------------------------
# Game list
# --------------------------------------------------------------------
class GameList(generics.ListAPIView):
    queryset = Game.objects.all()
    serializer_class = GameListSerializer
    filterset_class = GameFilter
    #filter_backends = (filters.DjangoFilterBackend, LimitFilterBackend)
    
    # if request.method == 'GET':
        
    #     # Έλεγχος για τα υποστηριζόμενα κριτήρια αναζήτησης
    #     games = Game.objects.all()
    #     ser = GameListSerializer(games, many=True, context={"request": request})
    #     return Response(ser.data)        

    # else:
    #     return Response(status=status.HTTP_400_BAD_REQUEST)


# --------------------------------------------------------------------
# Game details
# --------------------------------------------------------------------
@api_view(['GET'])
def game_details(request, game_id):
    game = get_object_or_404(Game, pk=game_id)
    ser = GameDetailsSerializer(game, many=False, context={"request": request})
    return Response(ser.data)      